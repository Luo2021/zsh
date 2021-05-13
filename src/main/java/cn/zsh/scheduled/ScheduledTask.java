package cn.zsh.scheduled;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import cn.zsh.mapper.ScheduleTimeMapper;
import cn.zsh.pojo.ScheduleTime;
import cn.zsh.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author luoli
 * @date 2021/4/21 15:17
 */
@Component
public class ScheduledTask {
    @Autowired
    private ScheduleTimeMapper scheduleTimeMapper;
    //每小时执行一次
    @Scheduled(cron = "0 0 * * * ?")
    //每一秒执行一次
    //@Scheduled(fixedDelay = 1000L )
    public void scheduled(){
        ScheduleTime scheduleTime = new ScheduleTime();
        String maxEndTime = scheduleTimeMapper.queryMaxEndTime();
        System.out.println("最大结束时间："+maxEndTime);
        if(StringUtils.isNotBlank(maxEndTime)) {
            scheduleTime.setStartTime(maxEndTime);
            scheduleTime.setEndTime(DateUtils.getNowTime());
        } else {
            scheduleTime.setStartTime(DateUtils.beforeOneHourToNowDate(0,1));
            scheduleTime.setEndTime(DateUtils.getNowTime());
        }
        try{
           scheduleTimeMapper.insert(scheduleTime);
        }catch(Exception e){
            if(e instanceof DuplicateKeyException){
                System.out.println("已经在另一台机器上执行定时任务，无需执行");
                return ;
            }else{
               System.out.println("插入数据库失败！"+ JSONUtil.toJsonStr(e.getStackTrace()));
                scheduleTimeMapper.delete(scheduleTime);
            }
        }
    }
}
