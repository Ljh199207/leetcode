package com.qwgas.fes.controller;

import com.alibaba.fastjson.JSONObject;
import com.qwgas.fes.response.FesResponse;
import com.qwgas.fes.service.MeterDataService;
import com.qwgas.fes.vo.MetreInfoVo;
import com.qwgas.fes.vo.param.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@Api(tags = "前置系统 Web API")
@RestController
@RequestMapping("/prepose")
public class MeterDataController {

    private final MeterDataService meterDataService;

    public MeterDataController(MeterDataService meterDataService) {
        this.meterDataService = meterDataService;
    }

    /**
     * post 搜索表具信息
     *
     * @return
     */
    @ApiOperation("搜索表具信息")
    @PostMapping(value = "searchMetreInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FesResponse searchMetreInfo(@RequestBody QueryMeterParam queryMeterParam) {
        MetreInfoVo metreInfoVo = meterDataService.searchMetreInfo(queryMeterParam);
        return new FesResponse().success().data(metreInfoVo);
    }

    /**
     * post 开关阀
     *
     * @return
     */
    @ApiOperation("开关阀")
    @PostMapping(value = "valveOperate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FesResponse valveOpera(@RequestBody ValveOperateParam valveOperateParam) {
        return meterDataService.valveOpera(valveOperateParam);
    }

    /**
     * post 更新余额及单价
     *
     * @return
     */
    @ApiOperation("更新余额和单价")
    @PostMapping(value = "updatePriceAndBalan", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FesResponse updatePriceAndBalan(@RequestBody UpdatePriceAndBalance updatePriceAndBalance) {
        return meterDataService.updatePriceAndBalan(updatePriceAndBalance);
    }


    /**
     * post 充值
     *
     * @return
     */
    @ApiOperation("充值")
    @PostMapping(value = "recharge", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FesResponse recharge(@RequestBody RechargeParam rechargeParam) {
        return meterDataService.recharge(rechargeParam);
    }

    /**
     * post 取消充值/冲正
     *
     * @return
     */
    @ApiOperation("取消充值")
    @PostMapping(value = "cancelRecharge", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FesResponse cancelRecharge(@RequestBody CancelRechargeParam rechargeParam) {
        return meterDataService.cancelRecharge(rechargeParam);
    }

    /**
     * post 表具信息下发
     *
     * @return
     */
    @ApiOperation("表具信息下发")
    @PostMapping(value = "meterInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FesResponse meterInfo(@RequestBody MeterInfoParam metreInfoVo) {
        return meterDataService.meterInfo(metreInfoVo);
    }

    /**
     * post 上报数据
     *
     * @return
     */
    @ApiOperation("上报数据")
    @PostMapping(value = "meterDataUp", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FesResponse meterDataUp(@RequestBody JSONObject jsonObject) {
        return meterDataService.meterDataUp(jsonObject);
    }

    /**
     * post 参数设置
     *
     * @return
     */
    @ApiOperation("参数设置")
    @PostMapping(value = "setParameter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FesResponse setParameter(@RequestBody ParameterUpParam parameterUpParam) {
        return meterDataService.setParameter(parameterUpParam);
    }

    /**
     * post 参数读取
     *
     * @return
     */
    @ApiOperation("参数读取")
    @PostMapping(value = "getParameter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FesResponse getParameter(@RequestBody ParameterParam parameterParam) {
        return meterDataService.getParameter(parameterParam);
    }

    /**
     * post 调价变价
     *
     * @return
     */
    @ApiOperation("调价变价")
    @PostMapping(value = "changeprice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FesResponse changePrice(@RequestBody ChangepriceParam changepriceParam) throws ParseException {
        return meterDataService.changePrice(changepriceParam);
    }

    /**
     * post 补抄
     *
     * @return
     */
    @ApiOperation("补抄")
    @PostMapping(value = "dataSupplement", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FesResponse dataSupplement(@RequestBody DataSupplementParam dataSupplementParam) {
        return meterDataService.dataSupplement(dataSupplementParam);
    }


    /**
     * post 报警上报数据
     *
     * @return
     */
    @ApiOperation("报警上报数据")
    @PostMapping(value = "sendAlarm", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public FesResponse sendAlarm(@RequestBody JSONObject jsonObject) {
        return meterDataService.sendAlarm(jsonObject);
    }

}
