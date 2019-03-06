package cn.cuslink.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author:zhangchundong
 * @Date:Create in 20:23 2019/3/5
 */
@Component
public class WxMpServiceConfig {

   @Autowired
   private WxAccountConfig wxAccountConifg;
   // 相当于xml文件中的bean标签
   @Bean
   public WxMpService getWxMpService(){
      WxMpService wxMpService = new WxMpServiceImpl();
      wxMpService.setWxMpConfigStorage(getWxMpConfigStotage());
      return wxMpService;
   }

   @Bean
   public WxMpConfigStorage getWxMpConfigStotage(){
      WxMpInMemoryConfigStorage memoryConfigStorage = new WxMpInMemoryConfigStorage();
      memoryConfigStorage.setAppId(wxAccountConifg.getMpAppId());
      memoryConfigStorage.setSecret(wxAccountConifg.getMpAppSecret());
      return memoryConfigStorage;
   }
}
