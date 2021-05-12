package cn.zsh.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author luoli
 * @date 2021/5/11 14:15
 */
@Data
@Table(name = "t_scheduleTime")
public class ScheduleTime {
    @Id
    @Column(name = "startTime")
    private String startTime;

    @Column(name = "endTime")
    private String endTime;
}
