package cn.cuslink.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 17:16 2019/3/2
 */
@Data
public class CreateOrderFromQD {
   @NotEmpty(message = "用户名不能为空")
   private String name;
   @NotEmpty(message = "手机号不能为空")
   private String phone;
   @NotEmpty(message = "地址不能为空")
   private String address;
   @NotEmpty(message = "微信用户不能为空")
   private String openid;

   private List<OrderDetail> items;
}
