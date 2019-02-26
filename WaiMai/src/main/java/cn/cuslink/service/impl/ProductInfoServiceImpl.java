package cn.cuslink.service.impl;

import cn.cuslink.dao.ProductInfoDao;
import cn.cuslink.pojo.ProductInfo;
import cn.cuslink.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 10:54 2019/2/26
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

   @Autowired
   private ProductInfoDao productInfoDao;

   @Override
   public int save(ProductInfo productInfo) {
      ProductInfo info = productInfoDao.save(productInfo);
      if (null != info) {
         return 1;
      }
      return 0;
   }

   @Override
   public List<ProductInfo> findByName(String productInfoName) {
      List<ProductInfo> list = productInfoDao.findByProductName(productInfoName);
      return list;
   }

   @Override
   public List<ProductInfo> findByType(int categoryType) {
      List<ProductInfo> list = productInfoDao.findByCategoryType(categoryType);
      return list;
   }

   @Override
   public List<ProductInfo> findByPage(int pageSize, int pageNum) {
      PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, "updateTime");
      Page<ProductInfo> infoPage = productInfoDao.findAll(pageRequest);
      return infoPage.getContent();
   }
}
