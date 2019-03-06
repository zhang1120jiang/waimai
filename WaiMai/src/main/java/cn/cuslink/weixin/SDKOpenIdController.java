package cn.cuslink.weixin;

import cn.cuslink.enums.OrderExceptionEnum;
import cn.cuslink.exception.OrderException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * @Author:zhangchundong
 * @Date:Create in 20:33 2019/3/5
 * SDK的方式进行获取到微信的openid
 */
@Controller
@RequestMapping("weixin")
@Slf4j
public class SDKOpenIdController {
   @Autowired
   private WxMpService wxMpService;

   @GetMapping("authorize")
   public String authorize(@RequestParam("returnUrl") String returnUrl) {
      //      首先构造网页授权url，
      log.info("【进入authorize方法】");
      String url = "http://5b68215.cpolar.io/sell/weixin/userInfo";
      // 然后构成超链接让用户点击：`
      // (url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null)
      String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
      return "redirect:"+redirectUrl;
   }

   @GetMapping("userInfo")
   private String getUserInfo(@RequestParam("code") String code,
                              @RequestParam("state") String returnUrl) {
      // 当用户同意授权后，会回调所设置的url并把authorization code传过来，
      // 然后用这个code获得access token，其中也包含用户的openid等信息
      //获得access token
      log.info("【进入getUserInfo】");
      WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
      String openId = "";
      try {
         wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
         // 获得用户基本信息
         WxMpUser userInfo = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
         log.info("【openid】，{}",userInfo.getOpenId());
         log.info("【City】，{}",userInfo.getCity());
         openId = userInfo.getOpenId();
      } catch (WxErrorException e) {
         log.error("【微信授权错误】");
         throw new OrderException(OrderExceptionEnum.WX_MP_ERROR);
      }
      return "redirect:"+returnUrl+"?openId="+openId;
   }
}
