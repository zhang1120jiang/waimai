package cn.cuslink.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:zhangchundong
 * @Date:Create in 15:38 2019/2/25
 * 商品详情表
 */
@Entity
@Table(name = "product_info")
@DynamicUpdate
@Data
public class ProductInfo {
   @Id
   @JsonProperty("id")
   private String productId;
   @JsonProperty("name")
   private String productName;
   @JsonProperty("price")
   private BigDecimal productPrice;
   private int productStock;
   @JsonProperty("description")
   private String productDescription;
   private int productStatus;
   private int categoryType;
   @JsonProperty("icon")
   private String productIcon;
   @Transient
   private Date createTime;
   @Transient
   private Date updateTime;
}

