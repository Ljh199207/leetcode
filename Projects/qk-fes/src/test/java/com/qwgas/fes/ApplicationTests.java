package com.qwgas.fes;

import com.alibaba.fastjson.JSON;
import com.qwgas.fes.test.RunService;
import com.qwgas.fes.util.HttpClientUtil;
import com.qwgas.fes.util.MD5Util;
import com.qwgas.fes.vo.param.MeterDataUpParam;
import com.qwgas.fes.vo.param.ResultParam;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ApplicationTests {

    @Autowired
    RunService runService;



    @Test
    public void getTest() throws URISyntaxException {
        //  runService.getTestGet();
        //runService.getTestPost();
        //runService.getTestPostParam();
        //  runService.getTestPut();
        //  runService.getTestDel();

      /*  SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        System.out.println(str);
        String appId ="WZQWApp";
        String appSecrect ="MIGfMA0GCSqGSIb3D4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
        String AccessToken =  MD5Util.MD5_32((appId+appSecrect+str));
        String Authorization = Base64.getEncoder().encodeToString((appId+":"+str).getBytes());

        System.out.println(AccessToken);
        System.out.println(Authorization);*/

        ResultParam resultParam = new ResultParam();
        resultParam.setMeterNo("1119053060220464");
        resultParam.setFactoryCode("1");
        resultParam.setMeterType("1");
        resultParam.setCommandSeq("111111");
        resultParam.setResultCode("0000");
      //  String message =   HttpClientUtil.returnPost("http://60.190.252.117:30348/rest/v1/collectionPlatform/openVResult", JSON.toJSONString(resultParam));
        String message1 =   HttpClientUtil.returnPost("http://60.190.252.117:30348/rest/v1/collectionPlatform/closeVResult", JSON.toJSONString(resultParam));
        System.out.println(message1);
    }

    @Test
    public void MeterDataUpServiceImplTest() {
        MeterDataUpParam meterDataUpParam = new MeterDataUpParam();
        meterDataUpParam.setMeterNo("123");
        meterDataUpParam.setCurrentCellVoltage("test");
        meterDataUpParam.setFactoryCode("fact");

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        String localTime = df.format(time);
        meterDataUpParam.setMeterTime(localTime);

        meterDataUpParam.setMeterType("21");

      //  meterDataUpService.meterDataUp(meterDataUpParam);
    }


    public HttpHeaders getHttpHeaders() {
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU3NTYzODE3MSwiaWF0IjoxNTc1NjE2NTcxfQ.91WGVsYcuxwuriDMZZ3JxuIYxLUGwwcCgv4BuajjMkq-sRqfQn4mmhwyZXFxhrOn5qXhxJDL1GTEc5_P3BVVGA";
        String accessToke = "";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        httpHeaders.add("Authorization", token);
        httpHeaders.add("AccessToke", accessToke);

        return httpHeaders;
    }
}
