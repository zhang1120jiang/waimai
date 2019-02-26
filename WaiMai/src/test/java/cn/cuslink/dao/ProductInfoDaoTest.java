package cn.cuslink.dao;

import cn.cuslink.pojo.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:zhangchundong
 * @Date:Create in 16:48 2019/2/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

   @Autowired
   private ProductInfoDao productInfoDao;

   @Test
   public void saveProductInfo(){
      ProductInfo productInfo = new ProductInfo();
      productInfo.setProductId("123456");
      productInfo.setCategoryType(1);
      productInfo.setProductDescription("好看的");
      productInfo.setProductName("adidas");
      productInfo.setProductStock(1000);
      productInfo.setProductPrice(new BigDecimal(2000));
      productInfo.setProductStatus(0);
      ProductInfo productInfo1 = productInfoDao.save(productInfo);
      System.out.println("producntInfo："+productInfo1);
   }

   @Test
   public void queryProductInfo(){
      List<ProductInfo> productInfoDaoAll = productInfoDao.findAll();
      System.out.println("productInfoAll："+productInfoDaoAll);
   }
}