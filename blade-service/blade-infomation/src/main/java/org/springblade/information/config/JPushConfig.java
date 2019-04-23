/**
 * Copyright (C), 2019-2019, 上海诺奚有限公司
 * FileName: JPushConfig
 * Author:   nuoee
 * Date:     2019/1/24 10:22
 * Description: 极光推送配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package org.springblade.information.config;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jsms.api.common.SMSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 * 〈极光推送配置〉
 *
 * @author nuoee
 * @create 2019/1/24
 * @since 1.0.0
 */
@Configuration
public class JPushConfig {
    @Value("${jpush.masterSecret}")
    private String masterSecret;

    @Value("${jpush.appKey}")
    private String appKey;


    @Bean(name = "jPushClient")
    public JPushClient jPushClient() {
        ClientConfig clientConfig = ClientConfig.getInstance();
        return new JPushClient(masterSecret, appKey, null, clientConfig);
    }

    @Bean(name = "smsClient")
    public SMSClient smsClient() {
        return new SMSClient(masterSecret, appKey);
    }
}
