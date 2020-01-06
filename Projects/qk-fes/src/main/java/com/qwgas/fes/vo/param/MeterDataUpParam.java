package com.qwgas.fes.vo.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author ljh
 * @date 2019-12-06 08:39
 * @desc 抄表数据
 */
@Data
public class MeterDataUpParam implements Serializable {

    /**
     * 表具编号（表钢号）
     */
    @NotNull
    private String meterNo;
    /**
     * 表厂编号，为表厂分配的内部编号
     */
    @NotNull
    private String factoryCode;

    /**
     * 表具型号，为表厂分配的表具型号
     */
    @NotNull
    private String meterType;

    /**
     * 表内时钟 yyyy-mm-dd HH24:MM:SS
     */
    @NotNull
    private String meterTime;

    /**
     * 阀门状态，0 开；1；关；2 异常；3 无阀
     * 门状态；4 普关
     */
    private String valveStatus;

    /**
     * 信号强度
     */
    private String signalStrength;
    /**
     * 电源类型，1 主电池 2 备份电池 3 主电
     * 源 4 备份电源
     */
    private String powerType;
    /**
     * 电池电压
     */
    private String currentCellVoltage;
    /**
     * 累计充值金额 表端计费金额表
     */
    private String totalRechargeAmt;
    /**
     * 累计充值次数 表具计费表
     */
    private String totalRechargeCount;
    /**
     * 累计充值气量，表具计费气量表
     */
    private String totalRechargeQty;
    /**
     * 温度
     */
    private String temperature;
    /**
     * 压力
     */
    private String pressure;
    /**
     * 抄表明细数据
     */
    private List<MeterReadingDetails> meterReadingDetails;

}
