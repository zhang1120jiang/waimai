package cn.cuslink.exception;

import cn.cuslink.enums.OrderExceptionEnum;
import lombok.Data;

/**
 * @Author:zhangchundong
 * @Date:Create in 11:20 2019/2/27
 * 自定义异常
 */
public class OrderException extends RuntimeException {
   private Integer code;
   private String errorMsg;

   public OrderException(OrderExceptionEnum orderExceptionEnum) {
      super(orderExceptionEnum.getMsg());
      this.code = orderExceptionEnum.getCode();
   }
}
