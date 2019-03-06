package cn.cuslink.enums;

import lombok.Getter;

/**
 * @Author:zhangchundong
 * @Date:Create in 17:43 2019/3/2
 */
@Getter
public enum ResultDataEnum {
   SUCCESS(0,"成功"),
   FAILER(1,"失败")
   ;
   private int code;
   private String msg;

   ResultDataEnum(int code, String msg) {
      this.code = code;
      this.msg = msg;
   }
}
