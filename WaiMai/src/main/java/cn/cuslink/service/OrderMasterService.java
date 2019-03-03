package cn.cuslink.service;

import cn.cuslink.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author:zhangchundong
 * @Date:Create in 16:57 2019/2/26
 */

public interface OrderMasterService {
   // 创建订单
   OrderMaster createOrder(OrderMaster orderMaster);
   // 取消订单
   OrderMaster cancleOrder(OrderMaster orderMaster);
   // 查询当个订单
   OrderMaster findOne(String orderId);
   // 查询订单列表
   Page<OrderMaster> findByPage(String opendId, Pageable pageable);
   // 完结订单
   OrderMaster finishOrder(OrderMaster orderMaster);
   // 支付订单
   OrderMaster payOrder(OrderMaster orderMaster);
   // 订单详情
   OrderMaster findOrderDetail(String orderId,String openid);
}
