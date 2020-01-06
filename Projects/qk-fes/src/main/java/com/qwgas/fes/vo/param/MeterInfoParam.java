package com.qwgas.fes.vo.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ljh
 * @date 2019-12-06 10:50
 */
@Data
public class MeterInfoParam implements Serializable {
    /**
     * 表具号
     */
    private String meterNo;

    /**
     * 用户号
     */
    private String userNo;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 集中器编号/通讯号
     */
    private String devNo ;

    /**
     * 公司编码
     */
    private String companyNo ;

    /**
     * 表具型号，为表厂分配的表具型号
     */
    private String meterType;
    /**
     * 地址(内容包含 街道、小区、楼栋、门牌
     * 号)
     */
    private String addrDes;

    /**
     * 安装时间 yyyy-mm-dd HH24:MM:SS
     */
    private String setUpTime ;

    /**
     * 表底数
     */
    private String enableMeterReading;
    /**
     * 表具操作，register:注册,modify：修改(暂
     * 时不用) ,remove：移除
     */
    private String meterOperation;
}
