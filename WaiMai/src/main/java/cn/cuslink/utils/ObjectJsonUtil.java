package cn.cuslink.utils;

import cn.cuslink.pojo.OrderMaster;
import cn.cuslink.pojo.ProductCategroy;
import cn.cuslink.pojo.ProductInfoJsonData;
import cn.cuslink.pojo.ResultData;
import lombok.Data;

import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 14:44 2019/2/26
 */
@Data
public class ObjectJsonUtil {

   public static ProductInfoJsonData getProductInfoData(Integer code
   ,String msg,List<ProductCategroy> list){
      ProductInfoJsonData jsonData = new ProductInfoJsonData();
      jsonData.setCode(code);
      jsonData.setMsg(msg);
      jsonData.setData(list);
      return jsonData;
   }
   public static ResultData getOrderMasterData(Integer code
   ,String msg,OrderMaster list){
      ResultData jsonData = new ResultData();
      jsonData.setCode(code);
      jsonData.setMsg(msg);
      jsonData.setData(list);
      return jsonData;
   }
}
