package cn.cuslink.controller;

import cn.cuslink.dao.OrderMasterDao;
import cn.cuslink.enums.OrderExceptionEnum;
import cn.cuslink.enums.ResultDataEnum;
import cn.cuslink.exception.OrderException;
import cn.cuslink.pojo.CreateOrderFromQD;
import cn.cuslink.pojo.OrderMaster;
import cn.cuslink.pojo.ResultData;
import cn.cuslink.service.OrderMasterService;
import cn.cuslink.utils.ObjectJsonUtil;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:zhangchundong
 * @Date:Create in 17:08 2019/3/2
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class OrderMasterController {

   @Autowired
   private OrderMasterService orderMasterService;
   @Autowired
   private OrderMasterDao orderMasterDao;
   //1：创建订单
   @PostMapping("create")
   public ResultData create(@RequestBody @Valid CreateOrderFromQD orderFromQD,
                              BindingResult result){
      // 发生了为空的数据
      if(result.hasErrors()){
         String message = result.getFieldError().getDefaultMessage();
         log.info("【创建订单】数据错误，{}数据为空",orderFromQD);
         throw new OrderException(OrderExceptionEnum.PARAM_ERROR);
      }
      // 设置值
      OrderMaster orderMaster = new OrderMaster();
      orderMaster.setBuyerName(orderFromQD.getName());
      orderMaster.setBuyerAddress(orderFromQD.getAddress());
      orderMaster.setBuyerOpenid(orderFromQD.getOpenid());
      orderMaster.setBuyerPhone(orderFromQD.getPhone());
      orderMaster.setOrderDetailList(orderFromQD.getItems());
      // 购物车没有商品
      if(orderMaster.getOrderDetailList().isEmpty()){
         log.info("【创建订单】购物车为空");
         throw new OrderException(OrderExceptionEnum.CART_IS_EMPTY);
      }
      // 创建订单
      OrderMaster order = orderMasterService.createOrder(orderMaster);
      return ObjectJsonUtil.getOrderMasterData(ResultDataEnum.SUCCESS.getCode()
              ,ResultDataEnum.SUCCESS.getMsg(),order);
   }

   /**
    * 通过opendid查询用户的订单列表
    * @param opendid
    * @param page
    * @param pageSize
    * @return
    */
   @GetMapping("list")
   public Map<String,Object> findList(@RequestParam("openid") String opendid,
                              @RequestParam("page") Integer page,@RequestParam("size") Integer pageSize){
      if(StringUtils.isEmpty(opendid)){
         log.error("【查询订单列表】，用户为空{}",opendid);
         throw new OrderException(OrderExceptionEnum.OPENDID_IS_EMPTY);
      }
      Pageable request = PageRequest.of(page, pageSize, Sort.Direction.DESC, "createTime");
      Page<OrderMaster> byPage = orderMasterService.findByPage(opendid, request);
      if(byPage.getContent().isEmpty()){
         log.info("【未找到用户的订单数据】");
      }
      Map<String,Object> map = new HashMap<String,Object>();
      map.put("code",ResultDataEnum.SUCCESS.getCode());
      map.put("msg",ResultDataEnum.SUCCESS.getMsg());
      map.put("data",byPage.getContent());
      return map;
   }

   /**
    * 订单详情
    * @param orderMaster
    * @return
    */
   @GetMapping("detail")
   public ResultData detail(String orderId,String openid){
      if(StringUtils.isEmpty(openid)){
         log.error("【查询订单详情】，用户为空{}",openid);
         throw new OrderException(OrderExceptionEnum.OPENDID_IS_EMPTY);
      }
      if(StringUtils.isEmpty(orderId)){
         log.error("【查询订单详情】，订单参数为空{}",orderId);
         throw new OrderException(OrderExceptionEnum.ORDERID_IS_EMPTY);
      }
      OrderMaster master = orderMasterService.findOrderDetail(orderId,openid);
      return ObjectJsonUtil.getOrderMasterData(ResultDataEnum.SUCCESS.getCode()
              ,ResultDataEnum.SUCCESS.getMsg(),master);
   }

   /**
    * 取消订单
    * @param orderId
    * @param openid
    * @return
    */
   @PostMapping("cancel")
   public ResultData cansleOrder(String orderId,String openid){
      if(StringUtils.isEmpty(openid)){
         log.error("【取消订单】，用户为空{}",openid);
         throw new OrderException(OrderExceptionEnum.OPENDID_IS_EMPTY);
      }
      if(StringUtils.isEmpty(orderId)){
         log.error("【取消订单】，订单参数为空{}",orderId);
         throw new OrderException(OrderExceptionEnum.ORDERID_IS_EMPTY);
      }
      OrderMaster byOrderId = orderMasterDao.findByOrderId(orderId);
      if(StringUtils.isEmpty(byOrderId)){
         log.info("【取消订单】，{}，未找到该订单",orderId);
         throw new OrderException(OrderExceptionEnum.ORDER_NOT_EXSITS);
      }
      if(!openid.equals(byOrderId.getBuyerOpenid())){
         log.error("【取消订单】，{}不是该订单用户",openid);
         throw new OrderException(OrderExceptionEnum.USER_ORDER_NOT_EQUAL);
      }
      OrderMaster master = orderMasterService.cancleOrder(byOrderId);
      log.info("【取消订单】，用户订单已取消");
      return ObjectJsonUtil.getOrderMasterData(ResultDataEnum.SUCCESS.getCode(),
              ResultDataEnum.SUCCESS.getMsg(),master);
   }

}

