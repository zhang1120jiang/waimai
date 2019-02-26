package cn.cuslink.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 15:32 2019/2/25
 * 商品类目表
 */
@Entity
@Table(name="product_category")
@DynamicUpdate // 时间更新才会生效
@Data // lombok会在编译期生成getter/setter方法
public class ProductCategroy {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer categoryId;
   @JsonProperty("name")
   private String categoryName;
   @JsonProperty("type")
   private Integer categoryType;
   @Transient
   private Date createTime;
   @Transient
   private Date updateTime;
   @Transient
   private List<ProductInfo> foods;
}
