package cn.cuslink.service;

import cn.cuslink.pojo.ProductCategroy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:zhangchundong
 * @Date:Create in 10:05 2019/2/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceTest {

   @Autowired
   private  ProductCategoryService productCategoryService;
   @Test
   public void findAll() {
      List<ProductCategroy> list = productCategoryService.findAll();
      System.out.println(list);
   }

   @Test
   public void save() {
      ProductCategroy productCategroy = new ProductCategroy();
      productCategroy.setCategoryType(1);
      productCategroy.setCategoryName("电器");
      int save = productCategoryService.save(productCategroy);
      System.out.println(save);
   }

   @Test
   public void findById() {
      // 没有匹配的记录会抛出异常
      ProductCategroy byId = productCategoryService.findById(1);
      System.out.println(byId);
   }
}