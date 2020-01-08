package com.qwgas.fes.vo.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ljh
 * @date 2019-12-06 09:00
 */
@Data
public class AlarmInfo implements Serializable {
    /**
     * 报警时间 yyyy-mm-dd HH24:MM:SS
     */
    @NotNull
    private String alarmTime;

    /**
     *报警类型 详见字典表（如果没有报警取
     * 消，类型传 0000 ）
     */
    @NotNull
    private String alarmType ;

    /**
     * 状态:0:报警中 ，1：报警取消
     */
    @NotNull
    private String alarmStatus;
}
