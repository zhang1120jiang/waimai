package cn.cuslink.service.impl;

import cn.cuslink.dao.OrderDetailDao;
import cn.cuslink.dao.OrderMasterDao;
import cn.cuslink.enums.OrderExceptionEnum;
import cn.cuslink.enums.OrderStatusEnum;
import cn.cuslink.enums.PayStatusEnum;
import cn.cuslink.exception.OrderException;
import cn.cuslink.pojo.OrderDetail;
import cn.cuslink.pojo.OrderMaster;
import cn.cuslink.pojo.ProductInfo;
import cn.cuslink.service.OrderMasterService;
import cn.cuslink.service.ProductInfoService;
import cn.cuslink.utils.StringPrimaryKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 10:39 2019/2/27
 */
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {
   @Autowired
   private ProductInfoService productInfoService;
   @Autowired
   private OrderMasterDao orderMasterDao;
   @Autowired
   private OrderDetailDao orderDetailDao;

   @Override
   @Transactional
   public OrderMaster createOrder(OrderMaster orderMaster) {
      // 1: 订单的单价
      List<OrderDetail> detailList = orderMaster.getOrderDetailList();
      orderMaster.setOrderId(StringPrimaryKeyUtil.createKey());
      BigDecimal decimal = new BigDecimal(0);
      for(int i = 0 ; i < detailList.size(); i++){
         // 得到所有商品的总价
         OrderDetail detail = detailList.get(i);
         ProductInfo byProductId = productInfoService.findByProductId(detail.getProductId());
         if(null == byProductId){
            throw new OrderException(OrderExceptionEnum.PRODUCT_NOT_EXIST);
         }
         // 2: 订单的总价
         decimal = byProductId.getProductPrice()
                 .multiply(new BigDecimal(detail.getProductQuantity()))
                 .add(decimal);
         // 3: 存入订单，订单详情
         detail.setDetailId(StringPrimaryKeyUtil.createKey());
         detail.setOrderId(orderMaster.getOrderId());
         detail.setProductPrice(byProductId.getProductPrice());
         detail.setProductName(byProductId.getProductName());
         detail.setProductIcon(byProductId.getProductIcon());
         OrderDetail save = orderDetailDao.save(detail);
         // 4: 减库存
         // 拿到商品进行判断库存是否足够
         // 这里会有线程安全问题
         int productStock = byProductId.getProductStock();
         if(detail.getProductQuantity() > productStock){
               throw new OrderException(OrderExceptionEnum.STOCK_NOT_ENOUGH);
         }
         productInfoService.decreaseStock(byProductId,detail.getProductQuantity());
      }
      // 订单
      orderMaster.setOrderAmount(decimal);
      orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
      orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
      OrderMaster save1 = orderMasterDao.save(orderMaster);
      return orderMaster;
   }

   /**
    * 取消订单
    * @param orderMaster
    * @return
    */
   @Override
   @Transactional
   public OrderMaster cancleOrder(OrderMaster orderMaster) {
      // 判断订单状态，新订单才能取消
      int status = orderMaster.getOrderStatus();
      if(status != OrderStatusEnum.NEW.getCode()){
         log.error("【取消订单】，{}订单不是新订单",orderMaster);
         throw new OrderException(OrderExceptionEnum.ORDER_NOT_NEW);
      }
      // 进行修改订单状态
      orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
      OrderMaster save = orderMasterDao.save(orderMaster);
      log.info("【取消订单】,{},订单已取消",save);
      // 返回库存,加库存操作
      List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderMaster.getOrderId());
      for (OrderDetail orderDetail:orderDetailList) {
         int productQuantity = orderDetail.getProductQuantity();
         ProductInfo productInfo = productInfoService.findByProductId(orderDetail.getProductId());
         productInfoService.increaseStock(productInfo,productQuantity);
      }
      //TODO 如果支付了，返回支付的金额
      int payStatus = orderMaster.getPayStatus();
      if(PayStatusEnum.SUCCESS.getCode().equals(payStatus)){
         // TODO 进行返回支付金额
      }
      return save;
   }

   /**
    * 查询单个订单
    * @param orderId
    * @return
    */
   @Override
   public OrderMaster findOne(String opendId) {
      List<OrderMaster> byBuyerOpenid = orderMasterDao.findByBuyerOpenid(opendId);
      return null;
   }

   /**
    * opendId查询订单列表
    * @param opendId
    * @param pageable
    * @return
    */
   @Override
   public Page<OrderMaster> findByPage(String opendId , Pageable pageable) {
     Page<OrderMaster> page =  orderMasterDao.findByBuyerOpenid(opendId,pageable);
      return page;
   }

   /**
    * 完结订单
    * @param orderMaster
    * @return
    */
   @Override
   public OrderMaster finishOrder(OrderMaster orderMaster) {
      // 判断订单状态
      int orderStatus = orderMaster.getOrderStatus();
      if(!OrderStatusEnum.NEW.getCode().equals(orderStatus)){
         log.error("【完结订单】，{}，订单不是新订单",orderMaster);
         throw new OrderException(OrderExceptionEnum.ORDER_NOT_NEW);
      }
      // 修改订单状态
      orderMaster.setOrderStatus(OrderStatusEnum.FINISH.getCode());
      OrderMaster save = orderMasterDao.save(orderMaster);
      if(null == save){
         log.error("【完结订单】，{}，订单修改错误",orderMaster);
         throw new OrderException(OrderExceptionEnum.ORDER_UPDATE_ERROR);
      }
      log.info("【完结订单】,订单完结");
      return orderMaster;
   }

   /**
    * 支付订单
    * @param orderMaster
    * @return
    */
   @Override
   public OrderMaster payOrder(OrderMaster orderMaster) {
      // 判断订单状态
      int orderStatus = orderMaster.getOrderStatus();
      if(!OrderStatusEnum.NEW.getCode().equals(orderStatus)){
         log.error("【支付订单】，{}，订单不是新订单",orderMaster);
         throw new OrderException(OrderExceptionEnum.ORDER_NOT_NEW);
      }
      // 判断支付状态
      int payStatus = orderMaster.getPayStatus();
      if(!PayStatusEnum.WAIT.getCode().equals(payStatus)){
         log.error("【支付订单】，{}，订单支付状态错误",orderMaster);
         throw new OrderException(OrderExceptionEnum.OPAY_STATUS_ERROR);
      }
      // 修改支付状态
      orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
      OrderMaster save = orderMasterDao.save(orderMaster);
      if(null == save){
         log.error("【支付订单】，{}，订单修改错误",orderMaster);
         throw new OrderException(OrderExceptionEnum.ORDER_UPDATE_ERROR);
      }
      log.info("【支付订单】,订单支付成功");
      return orderMaster;
   }

   /**
    * 订单详情
    * @param orderMaster
    * @return
    */
   @Override
   public OrderMaster findOrderDetail(String orderId,String openid) {
      OrderMaster master = orderMasterDao.findByOrderId(orderId);
      // 验证订单是否为当前用户
      if(!openid.equals(master.getBuyerOpenid())){
         log.error("【订单详情】，{}不是该订单用户",openid);
         throw new OrderException(OrderExceptionEnum.USER_ORDER_NOT_EQUAL);
      }
      List<OrderDetail> byOrderId = orderDetailDao.findByOrderId(orderId);
      master.setOrderDetailList(byOrderId);
      return master;
   }
}
