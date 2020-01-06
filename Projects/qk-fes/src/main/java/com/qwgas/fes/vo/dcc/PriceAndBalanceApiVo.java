package com.qwgas.fes.vo.dcc;

import lombok.Data;

import javax.validation.constraints.DecimalMin;

@Data
public class PriceAndBalanceApiVo  {

    private String meterType;

    @DecimalMin(value = "0", message = "price must be greater than or equal to 0")
    private Double price;

    @DecimalMin(value = "0", message = "price must be greater than or equal to 0")
    private Double balanceAmt;

    @DecimalMin(value = "0", message = "price must be greater than or equal to 0")
    private Double totalUseAmt;

    private String commandId;
}
