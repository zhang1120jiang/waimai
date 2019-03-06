package cn.cuslink.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author:zhangchundong
 * @Date:Create in 20:22 2019/3/5
 */
@Data
@ConfigurationProperties(prefix = "")
@Component
public class WxConfigStorage {
}
