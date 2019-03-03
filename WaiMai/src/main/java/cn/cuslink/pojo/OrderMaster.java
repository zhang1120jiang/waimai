package cn.cuslink.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 16:49 2019/2/26
 * 订单表
 */
@Data
@DynamicUpdate
@Table(name="order_master")
@Entity
public class OrderMaster {
   @Id
   private String orderId;
   private String buyerName;
   private String buyerPhone;
   private String buyerAddress;
   private String buyerOpenid;
   private BigDecimal orderAmount;
   private int orderStatus;
   private int payStatus;
   private Date createTime;
   private Date updateTime;
   @Transient
   private List<OrderDetail> orderDetailList;
}
