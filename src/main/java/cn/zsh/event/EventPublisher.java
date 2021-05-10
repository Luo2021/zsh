package cn.zsh.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author luoli
 * @date 2021/5/7 10:11
 */
@Component
public class EventPublisher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    // 发布事件消息；@Async异步处理
    @Async
    public void publisher(Map<String,String> msg) {
        applicationEventPublisher.publishEvent(new SmsEvent(this, msg));
    }
}
