package com.qwgas.fes.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qwgas.fes.response.FesResponse;
import com.qwgas.fes.util.HttpClientUtil;
import com.qwgas.fes.vo.param.MeterDataUpParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
public class TestController {


    @RequestMapping("/")
    String index() {

        String ss = HttpClientUtil.returnPost("http://60.190.252.117:30348/rest/v1/collectionPlatform/rechargeUp", JSON.toJSONString("gasPriceApiVO"));

        return ss;
    }


}
