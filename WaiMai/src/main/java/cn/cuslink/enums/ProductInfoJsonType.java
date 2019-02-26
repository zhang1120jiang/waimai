package cn.cuslink.enums;

import lombok.Getter;

/**
 * @Author:zhangchundong
 * @Date:Create in 15:18 2019/2/26
 */
@Getter
public enum ProductInfoJsonType {

   SUCCESS(0,"成功"),FAILURE(1,"失败");
   private Integer code;
   private String msg;
   ProductInfoJsonType(Integer code,String msg){
      this.code = code;
      this.msg = msg;
   }
}
