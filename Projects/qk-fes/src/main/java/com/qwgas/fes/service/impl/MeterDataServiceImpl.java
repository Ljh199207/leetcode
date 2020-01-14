package com.qwgas.fes.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qwgas.fes.config.ApiParam;
import com.qwgas.fes.response.FesResponse;
import com.qwgas.fes.service.CommonService;
import com.qwgas.fes.service.MeterDataService;
import com.qwgas.fes.util.*;
import com.qwgas.fes.vo.MetreInfoVo;
import com.qwgas.fes.vo.dcc.GasPriceApiVO;
import com.qwgas.fes.vo.dcc.PriceAndBalanceApiVo;
import com.qwgas.fes.vo.dcc.RechargeApiVO;
import com.qwgas.fes.vo.dcc.SearchMeter;
import com.qwgas.fes.vo.mapToStruct.MetreInfoVoConvter;
import com.qwgas.fes.vo.param.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MeterDataServiceImpl implements MeterDataService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private final ApiParam apiParam;

    private final RestTemplate restTemplate;
    private final RestTemplateUtils restTemplateUtils;

    private final MetreInfoVoConvter metreInfoVoConvter;

    private final TaskExecutor taskExecutor;

    @Autowired
    public MeterDataServiceImpl(ApiParam apiParam, RestTemplate restTemplate, RestTemplateUtils restTemplateUtils, MetreInfoVoConvter metreInfoVoConvter, CommonService commonService, TaskExecutor taskExecutor) {
        this.apiParam = apiParam;
        this.restTemplate = restTemplate;
        this.restTemplateUtils = restTemplateUtils;
        this.metreInfoVoConvter = metreInfoVoConvter;
        this.taskExecutor = taskExecutor;
    }

    @Override
    public MetreInfoVo searchMetreInfo(QueryMeterParam queryMeterParam) {
        String url = apiParam.getDccUrl() + apiParam.getSearchMetreInfo();
        String url2 = ParserUtil.parse("{", "}", url, queryMeterParam.getMeterNo());

        String s = HttpClientUtil.doGet(url2, TokenUtil.getToken());
        JSONObject jsonObject = JSONObject.parseObject(s);
        //   ResponseEntity responseEntity = restTemplateUtils.get(url2);
        JSONObject optional = Optional.ofNullable(jsonObject)
                .flatMap(jsonObj -> Optional.ofNullable(JSON.parseObject(jsonObj.toString()).getJSONObject("data")))
                .get();
        SearchMeter searchMeter = JSONObject.parseObject(String.valueOf(optional), SearchMeter.class);
        System.out.println(searchMeter);
        if (searchMeter != null) {
            MetreInfoVo metreInfoVo = metreInfoVoConvter.domain2dto(searchMeter);
            metreInfoVo.setMeterNo(queryMeterParam.getMeterNo());
            return metreInfoVo;
        }
        return null;
    }

    /**
     * 开关阀
     *
     * @param valveOperateParam 1
     * @return FesResponse
     */
    @Override
    public FesResponse valveOpera(ValveOperateParam valveOperateParam) {
        String url = apiParam.getDccUrl() + apiParam.getValveOpera();
        String url2 = ParserUtil.parse("{", "}", url, valveOperateParam.getMeterNo());
        ValveOperate valveOperate = new ValveOperate();
        if (StringUtils.isNotBlank(valveOperateParam.getValveOperate())) {
            switch (valveOperateParam.getValveOperate()) {
                //开
                case "0":
                    valveOperate.setType("1");
                    break;
                // 关
                case "1":
                    valveOperate.setType("3");
                    break;
                //普关
                case "2":
                    valveOperate.setType("2");
            }
        }
        String s = HttpClientUtil.doPut(url2, TokenUtil.getToken(), JSONObject.toJSONString(valveOperate));
        System.out.println(s);
        try {
            JSONObject jsonObject = JSONObject.parseObject(s);
            ResultParam resultParam = new ResultParam();

            if (jsonObject.containsKey("code") && ("0").equals(jsonObject.getString("code"))) {
                resultParam.setResultCode("00");
            } else {
                resultParam.setResultCode("01");
            }
            String commandId = Optional.ofNullable(jsonObject).flatMap(jsonObject1 -> Optional.ofNullable(jsonObject1.getJSONObject("data")))
                    .flatMap(jsonObject1 -> Optional.ofNullable(jsonObject1.getString("commandId"))).orElse("");
            resultParam.setCommandSeq(commandId);
            resultParam.setMeterNo(valveOperateParam.getMeterNo());
            resultParam.setMeterType(valveOperateParam.getMeterType());
            resultParam.setFactoryCode(valveOperateParam.getFactoryCode());
           /* // todo 开阀 返回操作
            if (("0").equals(valveOperateParam.getValveOperate())) {
                String ss = HttpClientUtil.returnPost(apiParam.getReturnUrl()+apiParam.getr, restTemplateUtils.getToken(), JSON.toJSONString(resultParam));
            }
            // todo 关阀 返回操作
            else {
                String ss = HttpClientUtil.returnPost("http://localhost:8080/tes", restTemplateUtils.getToken(), JSON.toJSONString(resultParam));
            }*/
            return FesResponseUtil.fesResponse(s);
        } catch (Exception e) {
            return new FesResponse().fail().message(e.getMessage());
        }
    }

    /**
     * 充值
     *
     * @param rechargeParam 11
     * @return FesResponse
     */
    @Override
    public FesResponse recharge(RechargeParam rechargeParam) {
        String url = apiParam.getDccUrl() + apiParam.getRecharge();
        String url2 = ParserUtil.parse("{", "}", url, rechargeParam.getMeterNo());
        RechargeApiVO rechargeApiVO = new RechargeApiVO();
        if (StringUtils.isNotBlank(rechargeParam.getRechargeType()) && ("1").equals(rechargeParam.getRechargeType())) {
            rechargeApiVO.setRechargeType(2);
        } else if (StringUtils.isNotBlank(rechargeParam.getRechargeType()) && ("2").equals(rechargeParam.getRechargeType())) {
            rechargeApiVO.setRechargeType(1);
        }
        rechargeApiVO.setAmount(Double.valueOf(rechargeParam.getRechargeNum()));
        taskExecutor.execute(() -> {
            String s = HttpClientUtil.doPost(url2, TokenUtil.getToken(), JSON.toJSONString(rechargeApiVO));
            System.out.println(s);
            try {
                JSONObject jsonObject = JSONObject.parseObject(s);
                //todo 返回数据
                RechargeUpParam rechargeUpParam = new RechargeUpParam();
                String commandId = Optional.ofNullable(jsonObject).flatMap(jsonObject1 -> Optional.ofNullable(jsonObject1.getJSONObject("data")))
                        .flatMap(jsonObject1 -> Optional.ofNullable(jsonObject1.getString("commandId"))).orElse("");
                String code = Optional.ofNullable(jsonObject).flatMap(jsonObject1 -> Optional.ofNullable(jsonObject1.getString("code"))).orElse("");
                rechargeUpParam.setCommandSeq(commandId);
                Object meterType = redisTemplate.opsForHash().get("MeterInfoParam", rechargeParam.getMeterNo());
                JSONObject json = (JSONObject) JSON.toJSON(meterType);
                rechargeUpParam.setFactoryCode(Optional.ofNullable(json).map(jsonObject2 -> jsonObject2.getString("companyNo")).orElse(""));
                rechargeUpParam.setMeterNo(rechargeParam.getMeterNo());
                rechargeUpParam.setMeterType(rechargeParam.getMeterType());
                rechargeUpParam.setTradeNo(rechargeParam.getTradeNo());
                rechargeUpParam.setRechargeNum(rechargeParam.getRechargeNum());
                rechargeUpParam.setMeterBalanceAmt(rechargeParam.getMeterBalanceAmt());
                if (StringUtils.isNotBlank(code) && ("0").equals(code)) {
                    rechargeUpParam.setResultCode("00");
                } else {
                    rechargeUpParam.setResultCode("01");
                }
                String ss = HttpClientUtil.returnPost(apiParam.getReturnUrl() + apiParam.getReturnRecharge(), JSON.toJSONString(rechargeUpParam));
                System.out.println(ss);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return new FesResponse().success().message("充值下发成功");
    }

    @Override
    public FesResponse changePrice(ChangepriceParam changepriceParam) throws ParseException {
        System.out.println(changepriceParam);
        String url = apiParam.getDccUrl() + apiParam.getChangePrice();
        String url2 = ParserUtil.parse("{", "}", url, changepriceParam.getMeterNo());
        GasPriceApiVO gasPriceApiVO = new GasPriceApiVO();
        if (StringUtils.isNotBlank(changepriceParam.getPriceLadderNum()) && ("1").equals(changepriceParam.getPriceLadderNum())) {
            gasPriceApiVO.setPriceTierCount(0);
        } else {
            gasPriceApiVO.setPriceTierCount(Integer.valueOf(changepriceParam.getPriceLadderNum()));
        }
        gasPriceApiVO.setTieredChargingCycle(Integer.valueOf(StringUtils.isNotBlank(changepriceParam.getBillingCycl()) ? changepriceParam.getBillingCycl() : "1"));
        if (StringUtils.isNotBlank(changepriceParam.getCycleStartDat())) {
            gasPriceApiVO.setChargingBeginTime(changepriceParam.getCycleStartDat());
        } else if (StringUtils.isNotBlank(changepriceParam.getEffectiveDate())) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf1.parse(changepriceParam.getEffectiveDate());
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = sdf2.format(date);
            gasPriceApiVO.setChargingBeginTime(str);
        }
        gasPriceApiVO.setTierPrice1(Double.valueOf(StringUtils.isNotBlank(changepriceParam.getPrice0()) ? changepriceParam.getPrice0() : "0.00"));
        gasPriceApiVO.setTierPrice2(Double.valueOf(StringUtils.isNotBlank(changepriceParam.getPrice1()) ? changepriceParam.getPrice1() : "0.00"));
        gasPriceApiVO.setTierPrice3(Double.valueOf(Double.valueOf(StringUtils.isNotBlank(changepriceParam.getPrice2()) ? changepriceParam.getPrice2() : "0.00")));
        gasPriceApiVO.setTierPrice4(Double.valueOf(Double.valueOf(StringUtils.isNotBlank(changepriceParam.getPrice3()) ? changepriceParam.getPrice3() : "0.00")));
        gasPriceApiVO.setTierPrice5(Double.valueOf(Double.valueOf(StringUtils.isNotBlank(changepriceParam.getPrice4()) ? changepriceParam.getPrice4() : "0.00")));
        if (StringUtils.isNotBlank(changepriceParam.getLevel1())) {
            gasPriceApiVO.setTierGas1(changepriceParam.getLevel1());
        }
        if (StringUtils.isNotBlank(changepriceParam.getLevel2())) {
            gasPriceApiVO.setTierGas2(changepriceParam.getLevel2());
        }
        if (StringUtils.isNotBlank(changepriceParam.getLevel3())) {
            gasPriceApiVO.setTierGas3(changepriceParam.getLevel3());
        }
        if (StringUtils.isNotBlank(changepriceParam.getLevel4())) {
            gasPriceApiVO.setTierGas4(changepriceParam.getLevel4());
        }
        taskExecutor.execute(() -> {
            String s = HttpClientUtil.doPut(url2, TokenUtil.getToken(), JSON.toJSONString(gasPriceApiVO));
            // todo  返回数据
            ResultParam resultParam = new ResultParam();
            resultParam.setMeterType(changepriceParam.getMeterType());
            resultParam.setMeterNo(changepriceParam.getMeterNo());
            Object meterType = redisTemplate.opsForHash().get("MeterInfoParam", changepriceParam.getMeterNo());
            JSONObject json = (JSONObject) JSON.toJSON(meterType);
            resultParam.setFactoryCode(Optional.ofNullable(json).map(jsonObject2 -> jsonObject2.getString("companyNo")).orElse(""));
            JSONObject jsonObject = JSONObject.parseObject(s);
            String code = Optional.ofNullable(jsonObject).flatMap(jsonObject1 -> Optional.ofNullable(jsonObject1.getString("code"))).orElse("");
            if (StringUtils.isNotBlank(code) && ("0").equals(code)) {
                resultParam.setResultCode("00");
            } else {
                resultParam.setResultCode("01");
            }
            String ss = HttpClientUtil.returnPost(apiParam.getReturnUrl() + apiParam.getReturnChangePrice(), JSON.toJSONString(resultParam));
            System.out.println(s);
        });
        return new FesResponse().success().message("调价下发成功！");
    }

    @Override
    public FesResponse updatePriceAndBalan(UpdatePriceAndBalance updatePriceAndBalance) {
        String url = apiParam.getDccUrl() + apiParam.getUpdatePriceAndBalance();
        String url2 = ParserUtil.parse("{", "}", url, updatePriceAndBalance.getMeterNo());
        PriceAndBalanceApiVo andBalanceApiVo = new PriceAndBalanceApiVo();
        andBalanceApiVo.setMeterType(updatePriceAndBalance.getMeterType());
        andBalanceApiVo.setBalanceAmt(StringUtils.isNotBlank(updatePriceAndBalance.getBalanceAmt()) ? Double.valueOf(updatePriceAndBalance.getBalanceAmt()) : 0.0);
        andBalanceApiVo.setPrice(StringUtils.isNotBlank(updatePriceAndBalance.getPrice()) ? Double.valueOf(updatePriceAndBalance.getPrice()) : 0.0);
        andBalanceApiVo.setCommandId(updatePriceAndBalance.getCommandSeq());
        andBalanceApiVo.setTotalUseAmt(StringUtils.isNotBlank(updatePriceAndBalance.getTotalUseAmt()) ? Double.valueOf(updatePriceAndBalance.getTotalUseAmt()) : 0.0);
        String s = HttpClientUtil.doPost(url2, TokenUtil.getToken(), JSON.toJSONString(andBalanceApiVo));
        // todo  返回数据
        System.out.println(s);
        return FesResponseUtil.fesResponse(s);
    }


    @Override
    public FesResponse cancelRecharge(CancelRechargeParam rechargeParam) {
        System.out.println(rechargeParam);


        String url = apiParam.getDccUrl() + apiParam.getRecharge();
        String url2 = ParserUtil.parse("{", "}", url, rechargeParam.getMeterNo());
        RechargeApiVO rechargeApiVO = new RechargeApiVO();
        //冲正，取消充值
        rechargeApiVO.setMode(2);
        rechargeApiVO.setAmount(Math.abs(Double.valueOf(rechargeParam.getRechargeNum())));
        taskExecutor.execute(() -> {
            String s = HttpClientUtil.doPost(url2, TokenUtil.getToken(), JSON.toJSONString(rechargeApiVO));
            //todo 返回数据
            RechargeUpParam rechargeUpParam = new RechargeUpParam();
            JSONObject jsonObject = JSONObject.parseObject(s);
            //todo 返回数据
            String commandId = Optional.ofNullable(jsonObject).flatMap(jsonObject1 -> Optional.ofNullable(jsonObject1.getJSONObject("data")))
                    .flatMap(jsonObject1 -> Optional.ofNullable(jsonObject1.getString("commandId"))).orElse("");
            rechargeUpParam.setCommandSeq(commandId);
            Object meterType = redisTemplate.opsForHash().get("MeterInfoParam", rechargeParam.getMeterNo());
            JSONObject json = (JSONObject) JSON.toJSON(meterType);
            rechargeUpParam.setFactoryCode(Optional.ofNullable(json).map(jsonObject2 -> jsonObject2.getString("companyNo")).orElse(""));
            rechargeUpParam.setMeterNo(rechargeParam.getMeterNo());
            rechargeUpParam.setMeterType(rechargeParam.getMeterType());
            rechargeUpParam.setRechargeNum(rechargeParam.getRechargeNum());
            rechargeUpParam.setTradeNo(rechargeParam.getTradeNo());
            String code = Optional.ofNullable(jsonObject).flatMap(jsonObject1 -> Optional.ofNullable(jsonObject1.getString("code"))).orElse("");
            if (StringUtils.isNotBlank(code) && ("0").equals(code)) {
                rechargeUpParam.setResultCode("00");
            } else {
                rechargeUpParam.setResultCode("01");
            }
            System.out.println(rechargeUpParam);
            String ss = HttpClientUtil.returnPost(apiParam.getReturnUrl() + apiParam.getReturnCancel(), JSON.toJSONString(rechargeUpParam));
            System.out.println(ss);
        });
        return new FesResponse().success().message("取消充值成功下发");
    }

    @Override
    public FesResponse meterInfo(MeterInfoParam metreInfoVo) {
        redisTemplate.opsForHash().put("MeterInfoParam", metreInfoVo.getMeterNo(), metreInfoVo);
        System.out.println(metreInfoVo);
        return new FesResponse().success().message("下发表具成功！");
    }

    @Override
    public FesResponse setParameter(ParameterUpParam parameterUpParam) {
        System.out.println(parameterUpParam);
        String url = apiParam.getDccUrl() + apiParam.getSParameter();
        String url2 = ParserUtil.parse("{", "}", url, parameterUpParam.getMeterNo());
        String s = HttpClientUtil.doPost(url2, TokenUtil.getToken(), JSON.toJSONString(parameterUpParam));
        System.out.println(s);


        return FesResponseUtil.fesResponse(s);
    }

    @Override
    public FesResponse getParameter(ParameterParam parameterParam) {
        String url = apiParam.getDccUrl() + apiParam.getGParameter();
        String url2 = ParserUtil.parse("{", "}", url, parameterParam.getMeterNo());
        taskExecutor.execute(() -> {
            String s = HttpClientUtil.doGet(url2, TokenUtil.getToken());
            System.out.println(s);
            JSONObject jsonObject = JSONObject.parseObject(s);
            JSONObject object = Optional.ofNullable(jsonObject)
                    .flatMap(jsonObject1 -> Optional.ofNullable(jsonObject.getJSONObject("data"))).orElse(null);
            ParameterUpParam parameterUpParam = JSONObject.parseObject(JSON.toJSONString(object), ParameterUpParam.class);
            Object meterType = redisTemplate.opsForHash().get("MeterInfoParam", parameterParam.getMeterNo());
            JSONObject json = (JSONObject) JSON.toJSON(meterType);
            parameterUpParam.setMeterType(Optional.ofNullable(json).map(jsonObject1 -> jsonObject1.getString("meterType")).orElse(""));
            String s1 = HttpClientUtil.returnPost(apiParam.getReturnUrl() + apiParam.getReturnParam(), JSON.toJSONString(parameterUpParam));
            System.out.println(s1);
        });

        return new FesResponse().success().message("参数读取下发成功");
    }

    @Override
    public FesResponse dataSupplement(DataSupplementParam dataSupplementParam) {
        return null;
    }


    @Override
    public FesResponse meterDataUp(JSONObject jsonObject) {
        try {
            MeterDataUpParam meterDataUpParam = new MeterDataUpParam();
            List<MeterReadingDetails> list = new ArrayList<>();
            Optional.ofNullable(jsonObject).flatMap(
                    jsonObject1 -> {
                        meterDataUpParam.setMeterNo(Optional.ofNullable(jsonObject1.getString("meterNo")).get());
                        meterDataUpParam.setFactoryCode(Optional.ofNullable(jsonObject1.getString("modelCode")).get());
                        // meterDataUpParam.setMeterType(Optional.ofNullable(jsonObject1.getString("modelCode")).get());
                        Object meterType = redisTemplate.opsForHash().get("MeterInfoParam", Optional.ofNullable(jsonObject1.getString("meterNo")).get());
                        JSONObject json = (JSONObject) JSON.toJSON(meterType);
                        meterDataUpParam.setMeterType(Optional.ofNullable(json).map(jsonObject2 -> jsonObject2.getString("meterType")).orElse(""));
                        meterDataUpParam.setValveStatus(convertValveStatus(Optional.ofNullable(jsonObject1.getString("valveStatus")).orElse("")));
                        meterDataUpParam.setSignalStrength(Optional.ofNullable(jsonObject1.getString("networkSignalQuality")).get());
                        meterDataUpParam.setPowerType("1");
                        meterDataUpParam.setCurrentCellVoltage(Optional.ofNullable(jsonObject1.getString("outBattery")).get());
                        meterDataUpParam.setTotalRechargeAmt(Optional.ofNullable(jsonObject1.getString("totalPurchaseFeeAmount")).orElse("0.0"));
                        meterDataUpParam.setTotalRechargeCount("");
                        meterDataUpParam.setTotalRechargeQty("");
                        meterDataUpParam.setTemperature("");
                        meterDataUpParam.setPressure("");
                        meterDataUpParam.setMeterTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        MeterReadingDetails meterReadingDetails = new MeterReadingDetails();
                        meterReadingDetails.setDataTime(Optional.ofNullable(jsonObject1.getString("meterReadTime")).get());
                        meterReadingDetails.setReadingNum(Optional.ofNullable(jsonObject1.getString("standardModeAmount")).get());
                        meterReadingDetails.setStandardNum(Optional.ofNullable(jsonObject1.getString("standardModeAmount")).get());
                        meterReadingDetails.setWorkNum(Optional.ofNullable(jsonObject1.getString("standardModeAmount")).get());
                        meterReadingDetails.setTotalUseAmt(Optional.ofNullable(jsonObject1.getString("totalPurchaseFeeAmount")).get());
                        meterReadingDetails.setMeterBalanceAmt(Optional.ofNullable(jsonObject1.getString("accountAmount")).orElse("0"));
                        meterReadingDetails.setMeterBalanceQty(Optional.ofNullable(jsonObject1.getString("gasBalance")).orElse("0"));
                        list.add(meterReadingDetails);
                        meterDataUpParam.setMeterReadingDetails(list);
                        System.out.println(jsonObject.toJSONString());
                        System.out.println(meterDataUpParam);
                        String ss = HttpClientUtil.returnPost(apiParam.getReturnUrl() + apiParam.getReturnDataUp(), JSON.toJSONString(meterDataUpParam));
                        System.out.println(ss);
                        return null;
                    }
            );
            return new FesResponse().success().data(meterDataUpParam);
        } catch (Exception e) {
            return null;
        }
    }

    private String convertValveStatus(String valveStatus) {
        String vs = "0";
        switch (valveStatus) {
            case "0":
                vs = "3";
                break;
            case "1":
                vs = "0";
                break;
            case "2":
            case "3":
                vs = "4";
                break;
            case "4":
                vs = "1";
                break;
            case "5":
            case "6":
                vs = "2";
                break;
        }
        return vs;
    }

    @Override
    public FesResponse sendAlarm(JSONObject jsonObject) {
        System.out.println(jsonObject.toJSONString());
        SendAlarmParam sendAlarmParam = new SendAlarmParam();
        List<AlarmInfo> list = new ArrayList<>();
        Optional.ofNullable(jsonObject).ifPresent(
                e -> {
                    sendAlarmParam.setFactoryCode(Optional.ofNullable(jsonObject.getString("factoryCode")).orElse(""));
                    sendAlarmParam.setMeterNo(Optional.ofNullable(jsonObject.getString("meterNo")).orElse(""));
                    Object meterType = redisTemplate.opsForHash().get("MeterInfoParam", Optional.ofNullable(jsonObject.getString("meterNo")).orElse(""));
                    JSONObject json = (JSONObject) JSON.toJSON(meterType);
                    sendAlarmParam.setMeterType(Optional.ofNullable(json).map(jsonObject1 -> jsonObject1.getString("meterType")).orElse(""));
                    AlarmInfo alarmInfo = new AlarmInfo();
                    alarmInfo.setAlarmStatus(Optional.ofNullable(jsonObject.getString("alarmStatus")).orElse(""));
                    alarmInfo.setAlarmTime(Optional.ofNullable(jsonObject.getString("alarmTime")).orElse(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())));
                    alarmInfo.setAlarmType(convertAlarmType(Optional.ofNullable(jsonObject.getString("alarmType")).orElse("")));
                    list.add(alarmInfo);
                    sendAlarmParam.setAlarms(list);
                }
        );
        String ss = HttpClientUtil.returnPost(apiParam.getReturnUrl() + apiParam.getSendAlarm(), JSON.toJSONString(sendAlarmParam));
        System.out.println(ss);
        return new FesResponse().data(sendAlarmParam);
    }

    public String convertAlarmType(String type) {
        String ty = "0000";
        switch (type) {
            case "0":
                ty = "1010";
                break;
            case "1":
            case "17":
            case "25":
            case "26":
            case "27":
            case "29":
            case "12":
            case "9":
            case "5":
            case "4":
            case "3":
            case "2":
            case "15":
            case "14":
            case "24":
            case "8":
                ty = "0000";
                break;
            case "6":
                ty = "1002";
                break;
            case "7":
                ty = "2001";
                break;
            case "10":
                ty = "1005";
                break;
            case "11":
                ty = "1003";
                break;
            case "13":
                ty = "1006";
                break;
            case "16":
                ty = "1001";
                break;
            case "18":
            case "19":
                ty = "1009";
                break;
            case "20":
            case "21":
                ty = "4001";
                break;
            case "22":
                ty = "1007";
                break;
            case "23":
                ty = "1004";
                break;
            case "28":
                ty = "2002";
                break;
            case "30":
                ty = "3002";
                break;
            case "31":
                ty = "3001";
                break;
            default:
                break;
        }
        return ty;
    }
}
