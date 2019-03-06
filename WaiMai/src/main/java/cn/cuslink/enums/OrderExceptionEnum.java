package cn.cuslink.enums;

import lombok.Getter;

/**
 * @Author:zhangchundong
 * @Date:Create in 11:22 2019/2/27
 */
@Getter
public enum OrderExceptionEnum {
   PRODUCT_NOT_EXIST(100, "商品不存在"),
   STOCK_NOT_ENOUGH(101, "商品库存不足"),
   PARAM_ERROR(102, "参数不正确"),
   CART_IS_EMPTY(103, "购物车没有商品"),
   OPENDID_IS_EMPTY(104, "用户微信id为空"),
   USER_ORDER_NOT_EQUAL(105, "该订单不为该用户"),
   ORDERID_IS_EMPTY(106, "订单参数为空"),
   ORDER_NOT_NEW(107, "该订单不是新订单"),
   ORDER_NOT_EXSITS(108, "订单不存在"),
   ORDER_UPDATE_ERROR(109, "订单更细错误"),
   OPAY_STATUS_ERROR(110,"订单支付状态错误" ),
   WX_MP_ERROR(111,"微信授权错误" );

   private Integer code;
   private String msg;

   OrderExceptionEnum(Integer code, String msg) {
      this.code = code;
      this.msg = msg;
   }
}
