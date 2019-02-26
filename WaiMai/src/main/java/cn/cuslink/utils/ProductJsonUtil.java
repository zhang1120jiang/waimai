package cn.cuslink.utils;

import cn.cuslink.pojo.ProductCategroy;
import cn.cuslink.pojo.ProductInfoJsonData;
import lombok.Data;

import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 14:44 2019/2/26
 */
@Data
public class ProductJsonUtil {

   public static ProductInfoJsonData getProductInfoData(Integer code
   ,String msg,List<ProductCategroy> list){
      ProductInfoJsonData jsonData = new ProductInfoJsonData();
      jsonData.setCode(code);
      jsonData.setMsg(msg);
      jsonData.setData(list);
      return jsonData;
   }
}
