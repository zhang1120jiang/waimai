package cn.cuslink.controller;

import cn.cuslink.enums.ProductInfoJsonType;
import cn.cuslink.pojo.ProductCategroy;
import cn.cuslink.pojo.ProductInfo;
import cn.cuslink.pojo.ProductInfoJsonData;
import cn.cuslink.service.ProductCategoryService;
import cn.cuslink.service.ProductInfoService;
import cn.cuslink.utils.ObjectJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author:zhangchundong
 * @Date:Create in 14:25 2019/2/26
 */
@Controller
@RestController
@RequestMapping("sell/buyer/product")
public class ProductCategoryController {

   @Autowired
   private ProductInfoService productInfoService;
   @Autowired
   private ProductCategoryService productCategoryService;

   @RequestMapping("list")
   public ProductInfoJsonData ProductInfoList(Integer pageNum, Integer pageSize){
      // 通过商品的类目查询所有的商品
      List<ProductCategroy> categoryServiceAll = productCategoryService.findAll();
      // 根据商品找到属于什么类目
      for(int i = 0 ; i < categoryServiceAll.size() ; i++){
         ProductCategroy productCategroy = categoryServiceAll.get(i);
         int categoryType = productCategroy.getCategoryType();
         List<ProductInfo> byType = productInfoService.findByType(categoryType);
         productCategroy.setFoods(byType);
      }
      ProductInfoJsonData productInfoData = ObjectJsonUtil.getProductInfoData(
              ProductInfoJsonType.SUCCESS.getCode(),
              ProductInfoJsonType.SUCCESS.getMsg(), categoryServiceAll);
      return productInfoData;
   }
}
