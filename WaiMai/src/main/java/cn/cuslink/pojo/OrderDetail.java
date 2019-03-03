package cn.cuslink.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Author:zhangchundong
 * @Date:Create in 17:03 2019/2/26
 */
@Data
@DynamicUpdate
@Table(name = "order_detail")
@Entity
public class OrderDetail {
   @Id
   private String detailId;
   private String orderId;
   private String productId;
   private String productName;
   private BigDecimal productPrice;
   private int productQuantity;
   private String productIcon;
}
