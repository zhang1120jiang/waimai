package cn.cuslink.dao;

import cn.cuslink.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 16:55 2019/2/26
 */

public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {
   /**
    * 根据用户的微信id查询用户订单
    * @param openid
    * @return
    */
   List<OrderMaster> findByBuyerOpenid(String openid);

   Page<OrderMaster> findByBuyerOpenid(String opendId, Pageable pageable);

   OrderMaster findByOrderId(String orderId);
}
