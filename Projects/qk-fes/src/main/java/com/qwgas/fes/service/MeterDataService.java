package com.qwgas.fes.service;

import com.alibaba.fastjson.JSONObject;
import com.qwgas.fes.response.FesResponse;
import com.qwgas.fes.vo.MetreInfoVo;
import com.qwgas.fes.vo.param.*;

import java.text.ParseException;


public interface MeterDataService {

    MetreInfoVo searchMetreInfo(QueryMeterParam queryMeterParam);

    FesResponse valveOpera(ValveOperateParam valveOperateParam);

    FesResponse recharge(RechargeParam rechargeParam);

    FesResponse changePrice(ChangepriceParam changepriceParam) throws ParseException;

    FesResponse updatePriceAndBalan(UpdatePriceAndBalance updatePriceAndBalance);

    FesResponse cancelRecharge(CancelRechargeParam rechargeParam);

    FesResponse meterInfo(MeterInfoParam metreInfoVo);

    FesResponse setParameter(ParameterUpParam parameterUpParam);

    FesResponse getParameter(ParameterParam parameterParam);

    FesResponse dataSupplement(DataSupplementParam dataSupplementParam);

    FesResponse meterDataUp(JSONObject jsonObject);

    FesResponse sendAlarm(JSONObject jsonObject);
}
