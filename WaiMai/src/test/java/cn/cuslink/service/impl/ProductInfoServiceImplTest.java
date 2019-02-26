package cn.cuslink.service.impl;

import cn.cuslink.pojo.ProductInfo;
import cn.cuslink.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 11:32 2019/2/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

   @Autowired
   private ProductInfoService productInfoService;
   @Test
   public void save() {
   }

   @Test
   public void findByName() {
   }

   @Test
   public void findByType() {
   }

   @Test
   public void findByPage() {
      List<ProductInfo> byPage = productInfoService.findByPage(5, 0);
      System.out.println(byPage);
   }
}