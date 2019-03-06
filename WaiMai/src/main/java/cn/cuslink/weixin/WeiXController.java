package cn.cuslink.weixin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @Author:zhangchundong
 * @Date:Create in 19:31 2019/3/4
 */
@Controller
@Slf4j
public class WeiXController {

   private static final String token = "42571535zhang";
   private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
           '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
   @GetMapping("weixin")
   public void weixin(HttpServletRequest req, HttpServletResponse resp)
           throws Exception {
      String signature = req.getParameter("signature");
      log.info("【signature】,{}",signature);
      String timestamp = req.getParameter("timestamp");
      log.info("【timestamp】,{}",timestamp);
      String nonce = req.getParameter("nonce");
      log.info("【nonce】,{}",nonce);
      String echostr = req.getParameter("echostr");
      log.info("【echostr】,{}",echostr);
      //1）将token、timestamp、nonce三个参数进行字典序排序
      String[] arr = {token, timestamp, nonce};
      Arrays.sort(arr);
      //2）将三个参数字符串拼接成一个字符串进行sha1加密
      String  str = "";
      for (String arrStr : arr ) {
         str += arrStr;
      }
      log.info("【拼接三个参数字符串】，{}",str);
      MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
      sha1.update(str.getBytes());
      byte[] digest = sha1.digest();
      StringBuffer hexString = new StringBuffer();
      //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
      for (int i = 0 ; i < digest.length ; i++){
         hexString.append(HEX_DIGITS[(digest[i] >> 4) & 0x0f]);
         hexString.append(HEX_DIGITS[digest[i] & 0x0f]);
      }
      String sha1Str = hexString.toString();
      log.info("【sha1后的字符串】，{}",sha1Str);
      if(sha1Str.equals(signature)){
         log.info("【来源于微信】");
         PrintWriter writer = resp.getWriter();
         writer.write(echostr);
      }else{
         log.error("【不是来源于微信】");
      }
   }
}
