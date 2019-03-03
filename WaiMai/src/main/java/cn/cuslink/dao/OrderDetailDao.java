package cn.cuslink.dao;

import cn.cuslink.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 17:12 2019/2/26
 */

public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {
   /**
    * 根据订单查询订单详情
    * @param orderId
    * @return
    */
   List<OrderDetail> findByOrderId(String orderId);
}
