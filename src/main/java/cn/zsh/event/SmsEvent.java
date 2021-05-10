package cn.zsh.event;

import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * @author luoli
 * @date 2021/5/7 10:14
 */
@EqualsAndHashCode(callSuper = false)
public class SmsEvent extends ApplicationEvent {
    //自定义消息
   private Map<String,String> msg;

    public SmsEvent(Object source,Map<String,String> msg) {
        super(source);
        this.msg = msg;
    }
    public Map<String,String> getMessage(){
        return  this.msg;
    }
    public void setMessage(Map<String,String> msg){
        this.msg = msg;
    }
}
