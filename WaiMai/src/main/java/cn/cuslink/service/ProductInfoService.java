package cn.cuslink.service;

import cn.cuslink.pojo.ProductInfo;

import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 10:15 2019/2/26
 */

public interface ProductInfoService {
   //保存商品
   int save(ProductInfo productInfo);
   // 根据商品名称查询，模糊查询
   List<ProductInfo> findByName(String productInfoName);
   // 根据类目查询商品
   List<ProductInfo> findByType(int categoryType);
   // 分页查询
   List<ProductInfo> findByPage(int pageSize, int pageNum);
   // 加库存
   // 减库存
}
