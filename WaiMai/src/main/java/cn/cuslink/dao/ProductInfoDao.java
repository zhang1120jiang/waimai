package cn.cuslink.dao;

import cn.cuslink.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 16:44 2019/2/25
 */

public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {

   ProductInfo findByProductId(String productId);

   List<ProductInfo> findByCategoryType(int categoryType);

   List<ProductInfo> findByProductName(String productName);
}
