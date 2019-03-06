package cn.cuslink.utils;

import java.util.Random;

/**
 * @Author:zhangchundong
 * @Date:Create in 11:56 2019/2/27
 * 用于生成主键类型为String的工具类
 */

public class StringPrimaryKeyUtil {

   public static String createKey(){
      // 当前时间
      long timeMillis = System.currentTimeMillis();
      // 随机数,四位数的随机数
      Random random = new Random();
      int fourBit = random.nextInt(10000) + 1000;
      String id = String.valueOf(fourBit) + String.valueOf(timeMillis);
      return id;
   }
}
