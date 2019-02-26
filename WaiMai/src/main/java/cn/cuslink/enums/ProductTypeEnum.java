package cn.cuslink.enums;

import lombok.Getter;

/**
 * @Author:zhangchundong
 * @Date:Create in 14:07 2019/2/26
 * 自定义枚举类用于商品的状态
 */
@Getter
public enum  ProductTypeEnum {
   UP(0,"上架商品"),
   DOWN(1,"下架商品")
   ;
   private Integer code;
   private String message;

   ProductTypeEnum(Integer code,String message){
      this.code = code;
      this.message = message;
   }
}
