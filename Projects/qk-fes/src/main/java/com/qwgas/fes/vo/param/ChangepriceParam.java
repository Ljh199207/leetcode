package com.qwgas.fes.vo.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ljh
 * @date 2019-12-06 10:40
 */
@Data
public class ChangepriceParam  implements Serializable {
    /**
     * 表具编号（表钢号）
     */
    @NotNull
    private String meterNo;

    /**
     * 表具型号，为表厂分配的表具型号
     */
    @NotNull
    private String meterType;

    /**
     * 指令序列号
     */
    @NotNull
    private String commandSeq;

    /**
     * 价格名称
     */
    @NotNull
    private String priceName;

    /**
     * 价格版本
     */
    @NotNull
    private String priceVersion;

    /**
     * 阶梯级数，单一价=1
     */
    @NotNull
    private String priceLadderNum;

    /**
     * 阶梯价计费周期
     */
    private String billingCycl;

    /**
     * 调价日期（生效日期）yyyy-mm-dd
     */
    @NotNull
    private String effectiveDate;

    /**
     * 周期开始日期 yyyy-mm-dd
     * HH24:MM:SS
     */
    private String cycleStartDat;

    /**
     * 单价0
     */
    @NotNull
    private String price0 ;

    /**
     * 单价1
     */
    private String price1;

    /**
     * 单价2
     */
    private String price2;

    /**
     * 单价3
     */
    private String price3;

    /**
     * 单价4
     */
    private String price4;

    /**
     * 阶梯气量 0
     */
    private String level1 ;
    /**
     * 阶梯气量 1
     */
    private String level2 ;
    /**
     * 阶梯气量 2
     */
    private String level3 ;
    /**
     * 阶梯气量 3
     */
    private String level4 ;
    /**
     * 调价次数
     */
    private String priceNum;
}
