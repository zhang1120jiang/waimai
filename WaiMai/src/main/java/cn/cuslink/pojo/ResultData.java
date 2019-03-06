package cn.cuslink.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 17:12 2019/3/2
 * 返回时的参数对象
 */
@Data
public class ResultData {
   private int code;
   private String msg;
   private OrderMaster data;
}
