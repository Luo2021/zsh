package cn.zsh.mapper;

import cn.zsh.pojo.ScheduleTime;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author luoli
 * @date 2021/5/11 14:26
 */
public interface ScheduleTimeMapper extends Mapper<ScheduleTime> {

    @Select("select max(endTime) from t_scheduleTime ")
    String queryMaxEndTime();
}
