package cn.cuslink.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 15:14 2019/2/26
 */
@Data
public class ProductInfoJsonData {

   private Integer code;
   private String msg;
   private List<ProductCategroy> data;
}
