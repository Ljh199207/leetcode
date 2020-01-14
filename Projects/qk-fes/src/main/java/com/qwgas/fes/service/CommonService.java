package com.qwgas.fes.service;

import com.alibaba.fastjson.JSONObject;
import com.qwgas.fes.response.FesResponse;

/**
 * @author ljh
 * @date 2019-12-09 16:36
 */
public interface CommonService {
    /**
     * 获取dcc token 值
     *
     * @param jsonParam
     * @return
     */
    FesResponse getToken(JSONObject jsonParam) throws Exception;
}
