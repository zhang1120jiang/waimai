package cn.cuslink.enums;

import lombok.Getter;

/**
 * @Author:zhangchundong
 * @Date:Create in 9:52 2019/2/27
 * 订单状态
 */
@Getter
public enum OrderStatusEnum {
   NEW(0,"新订单"),FINISH(1,"订单完成"),CANCEL(2,"取消订单");
   private Integer code;
   private String msg;

   OrderStatusEnum(Integer code, String msg) {
      this.code = code;
      this.msg = msg;
   }
}
