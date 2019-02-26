package cn.cuslink.dao;

import cn.cuslink.pojo.ProductCategroy;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Entity;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:zhangchundong
 * @Date:Create in 15:58 2019/2/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

   @Autowired
   private ProductCategoryDao productCategoryDao;
   @Test
   public void categoryTest(){
      ProductCategroy productCategroy = new ProductCategroy();
      productCategroy.setCategoryName("鞋子");
      productCategroy.setCategoryType(1);
      ProductCategroy save = productCategoryDao.save(productCategroy);
      System.out.println("save："+save);
   }
   @Test
   public void categoryQuery(){
      List<ProductCategroy> productCategoryDaoAll = productCategoryDao.findAll();
      System.out.println(productCategoryDaoAll);
   }
}
