package com.qwgas.fes.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ljh
 * @date 2019-12-09 09:59
 */
@Configuration
@ConfigurationProperties(prefix = "api")
@Data
public class ApiParam {
    /**
     * 应用唯一标识id
     */
    private String serviceId;
    /**
     * 应用密钥
     */
    private String serviceSecret;
    /**
     * http url
     */
    private String url;
    /**
     * http 抄表数据上报
     */
    private String meterDataUp;
    /**
     * http 批量抄表数据上报
     */
    private String meterDataUpBatch;
    /**
     * http 补抄数据上报
     */
    private String meterDataSupplement;
    /**
     * http 报警数据上报
     */
    private String sendAlarm;

    /**
     * http 搜索表具信息
     */
    private String searchMetreInfo;
    /**
     * 开关阀
     */
    private String valveOpera;
    /**
     * 充值
     */
    private String recharge;
    /**
     * 调价变价
     */
    private String changePrice;

    /**
     * dcc 采集系统
     */
    private String dccUrl;
    /**
     * dcc 采集系统获取token
     */
    private String tokenUrl;

    /**
     * 更新余额和单价
     */
    private String updatePriceAndBalance;
    /**
     * 表具信息下发
     */
    private String meterInfo;
    /**
     * 表具开通(威星表厂)
     */
    private String openAccount;

    /**
     * 获取参数
     *
     */
    private  String gParameter;
    /**
     * 设置参数
     *
     */
    private  String sParameter;

    /**
     * 充值返回
     *
     */
    private  String returnRecharge;

    /**
     * 返回地址
     */
   private String returnUrl;

    /**
     * 返回参数读取
     */
    private String returnParam;
    /**
     * 取消充值上报
     */
    private String returnCancel;

    /**
     * 数据上报
     */
    private String returnDataUp;

    /**
     * 数据上报
     */
    private String returnChangePrice;
}
