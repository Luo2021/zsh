package cn.zsh.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author luoli
 * @date 2021/4/21 15:17
 */
@Component
public class ScheduledTask {

    @Scheduled(cron = "0 0/1 9 * * ?" )
    public void scheduled(){
        try{
            String s="";
            Integer.parseInt(s);
        }catch(Exception e){
            System.out.println("LUOLI"+new Date());
        }
    }
}
