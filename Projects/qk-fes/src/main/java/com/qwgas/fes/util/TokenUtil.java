package com.qwgas.fes.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qwgas.fes.config.ApiParam;
import com.qwgas.fes.response.FesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Optional;

@Component
public class TokenUtil {

    public static ApiParam apiParam;

    private static RestTemplate restTemplate;

    @Autowired
    public void setApiParam(ApiParam api, RestTemplate rest) {
        apiParam = api;
        restTemplate = rest;
    }
    public static String getToken() {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("serviceId", apiParam.getServiceId());
        jsonParam.put("serviceSecret", apiParam.getServiceSecret());
        String tokenUrl = apiParam.getDccUrl() + apiParam.getTokenUrl();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(tokenUrl);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity(null, new HttpHeaders());
        Map<String, String> params = JSONObject.parseObject(JSONObject.toJSONString(jsonParam), Map.class);
        //如果存在參數
        if (!params.isEmpty()) {
            for (Map.Entry<String, String> e : params.entrySet()) {
                //构建查询参数
                builder.queryParam(e.getKey(), e.getValue());
            }
            //拼接好参数后的URl//test.com/url?param1={param1}&param2={param2};
            String reallyUrl = builder.build().toString();
            ResponseEntity<String> responseEntity = restTemplate.exchange(reallyUrl, HttpMethod.GET, request, String.class);
            FesResponse fesResponse = new FesResponse();
           String token = Optional.ofNullable(responseEntity)
                    .flatMap(obj -> Optional.ofNullable(obj.getBody()))
                    .flatMap(jsonObj -> {
                        return Optional.ofNullable(JSON.parseObject(jsonObj.toString()).getJSONObject("data"));
                    })
                    .flatMap(jsonObj -> {
                        return Optional.ofNullable(jsonObj.getString("token"));
                    }).get();
            return token;
        } else {
            return null;
        }
    }
}
