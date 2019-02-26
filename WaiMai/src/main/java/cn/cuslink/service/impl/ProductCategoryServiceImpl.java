package cn.cuslink.service.impl;

import cn.cuslink.dao.ProductCategoryDao;
import cn.cuslink.pojo.ProductCategroy;
import cn.cuslink.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author:zhangchundong
 * @Date:Create in 9:38 2019/2/26
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

   @Autowired
   private ProductCategoryDao productCategoryDao;

   @Override
   public List<ProductCategroy> findAll() {
      List<ProductCategroy> categroyList = productCategoryDao.findAll();
      return categroyList;
   }

   @Override
   public int save(ProductCategroy productCategroy) {
      ProductCategroy save = productCategoryDao.save(productCategroy);
      if(null != save){
         return 1;
      }
      return 0;
   }

   @Override
   public ProductCategroy findById(int categoryId) {
      Optional<ProductCategroy> byId = productCategoryDao.findById(categoryId);
      ProductCategroy categroy = byId.get();
      return categroy;
   }

   @Override
   public ProductCategroy findByCategoryType(int categoryType) {
      return productCategoryDao.findByCategoryType(categoryType);
   }
}
