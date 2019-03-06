package cn.cuslink.enums;

import lombok.Getter;

/**
 * @Author:zhangchundong
 * @Date:Create in 9:54 2019/2/27
 * 支付状态
 */
@Getter
public enum PayStatusEnum {
   WAIT(0,"待支付"),SUCCESS(1,"支付成功");
   private Integer code;
   private String msg;

   PayStatusEnum(Integer code, String msg) {
      this.code = code;
      this.msg = msg;
   }
}
