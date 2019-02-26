package cn.cuslink.service;

import cn.cuslink.pojo.ProductCategroy;

import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 9:30 2019/2/26
 */

public interface ProductCategoryService {

   List<ProductCategroy> findAll();

   int save(ProductCategroy productCategroy);

   ProductCategroy findById(int categoryId);

   ProductCategroy findByCategoryType(int categoryType);
}
