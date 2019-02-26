package cn.cuslink.dao;

import cn.cuslink.pojo.ProductCategroy;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:zhangchundong
 * @Date:Create in 15:46 2019/2/25
 */

public interface ProductCategoryDao extends JpaRepository<ProductCategroy,Integer>{

   ProductCategroy findByCategoryType(int categoryType);
}
