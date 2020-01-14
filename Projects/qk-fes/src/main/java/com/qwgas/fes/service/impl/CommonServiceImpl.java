package com.qwgas.fes.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qwgas.fes.config.ApiParam;
import com.qwgas.fes.response.FesResponse;
import com.qwgas.fes.service.CommonService;
import com.qwgas.fes.util.HttpClientUtil;
import com.qwgas.fes.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

/**
 * @author ljh
 * @date 2019-12-09 16:36
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Resource
    private ApiParam apiParam;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * @param jsonParam
     * @return
     */
    @Override
    public FesResponse getToken(JSONObject jsonParam) throws Exception {
        String tokenUrl = apiParam.getDccUrl() + apiParam.getTokenUrl();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(tokenUrl);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity(null, new HttpHeaders());
        Map<String, String> params = JSONObject.parseObject(JSONObject.toJSONString(jsonParam), Map.class);
        //如果存在參數
        if (!params.isEmpty()) {
            for (Map.Entry<String, String> e :
                    params.entrySet()) {
                //构建查询参数
                builder.queryParam(e.getKey(), e.getValue());
            }
            //拼接好参数后的URl//test.com/url?param1={param1}&param2={param2};
            String reallyUrl = builder.build().toString();
            ResponseEntity<String> responseEntity = restTemplate.exchange(reallyUrl, HttpMethod.GET, request, String.class);
            FesResponse fesResponse = new FesResponse();
            Optional.ofNullable(responseEntity)
                    .flatMap(obj -> Optional.ofNullable(obj.getBody()))
                    .flatMap(jsonObj -> {
                        fesResponse.message(Optional.ofNullable(JSON.parseObject(jsonObj.toString()).getString("message")).orElse(""));
                        fesResponse.put("returnCode", Optional.ofNullable(JSON.parseObject(jsonObj.toString()).getString("code")).get());
                        return Optional.ofNullable(JSON.parseObject(jsonObj.toString()).getJSONObject("data"));
                    })
                    .flatMap(jsonObj -> {
                        fesResponse.success().put("token", Optional.ofNullable(jsonObj.getString("token")).orElse(""));
                        return Optional.ofNullable(jsonObj.getString("token"));
                    });
            return fesResponse;
        } else {
            return null;
        }
    }


}
