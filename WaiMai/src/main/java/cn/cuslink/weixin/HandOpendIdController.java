package cn.cuslink.weixin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @Author:zhangchundong
 * @Date:Create in 17:12 2019/3/5
 */
@Controller
@Slf4j
@RequestMapping("weixin")
public class HandOpendIdController {

   @GetMapping("auth")
   public void auth(String code){
      log.info("【进入auth】");
      log.info("【code】,{}",code);
      String url="https://api.weixin.qq.com/sns/oauth2/access_token?"
              +"appid=wx79716cc13fa55091&secret=d7e5fc78fe934e97670e8fe6d358327e&code="+ code +"&grant_type=authorization_code";
      RestTemplate restTemplate = new RestTemplate();
      String response=restTemplate.getForObject(url,String.class);
      log.info("response={}",response);
   }
}
