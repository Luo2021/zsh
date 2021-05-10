package cn.zsh.listener;

import cn.zsh.event.SmsEvent;
import cn.zsh.pojo.SmsProperties;
import cn.zsh.utils.SmsUtils;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author luoli
 * @date 2021/5/7 10:14
 * @Feature:短信服务监听器
 */
@Component
@Slf4j
public class SmsListener implements ApplicationListener<SmsEvent> {

    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private SmsProperties smsProperties;

    @Override
    public void onApplicationEvent(SmsEvent smsEvent) {
        Map<String,String> msg = smsEvent.getMessage();
        listenSms(msg);
    }


    public void listenSms(Map<String,String> msg){
        if (msg == null || msg.size() <= 0){
            //不做处理
            return;
        }
        String phone = msg.get("phone");
        String code = msg.get("code");

        if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(code)){
            //发送消息
            try {
                SendSmsResponse response = this.smsUtils.sendSms(phone, code, smsProperties.getSignName(), smsProperties.getVerifyCodeTemplate());
            }catch (ClientException e){
                log.error(e.getMessage());
                return;
            }
        }else {
            //不做处理
            return;
        }
    }

}
