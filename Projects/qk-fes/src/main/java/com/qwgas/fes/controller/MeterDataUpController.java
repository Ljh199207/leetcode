package com.qwgas.fes.controller;

import com.alibaba.fastjson.JSONObject;
import com.qwgas.fes.response.FesResponse;
import com.qwgas.fes.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljh
 * @date 2019-12-09 14:27
 */
@Api(tags = "获取token")
@RestController
@RequestMapping("/fes")
public class MeterDataUpController {

    private CommonService commonService;
    @Autowired
    public MeterDataUpController(CommonService commonService) {
        this.commonService = commonService;
    }

    /**
     * get 获取token
     *
     * @return
     */
    @ApiOperation("获取token")
    @GetMapping(value = "token/{serviceId}/{serviceSecret}")
    public FesResponse getToken(@PathVariable(value = "serviceId") String serviceId, @PathVariable(value = "serviceSecret") String serviceSecret) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("serviceId", serviceId);
        jsonObject.put("serviceSecret", serviceSecret);
        return commonService.getToken(jsonObject);
    }
}
