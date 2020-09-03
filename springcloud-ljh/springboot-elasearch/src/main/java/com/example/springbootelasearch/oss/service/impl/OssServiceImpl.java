package com.example.springbootelasearch.oss.service.impl;

import com.example.springbootelasearch.oss.result.OssCallbackResult;
import com.example.springbootelasearch.oss.result.OssPolicyResult;
import com.example.springbootelasearch.oss.service.OssService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class OssServiceImpl implements OssService {
    @Value("${aliyun.oss.policy.expire}")
    private int ALIYUN_OSS_EXPIRE;
    @Value("${aliyun.oss.maxSize}")
    private int ALIYUN_OSS_MAX_SIZE;
    @Value("${aliyun.oss.callback}")
    private String ALIYUN_OSS_CALLBACK;
    @Value("${aliyun.oss.bucketName}")
    private String ALIYUN_OSS_BUCKET_NAME;
    @Value("${aliyun.oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;
    @Value("${aliyun.oss.dir.prefix}")
    private String ALIYUN_OSS_DIR_PREFIX;


    @Override
    public OssPolicyResult policy() {
       // ossClient.generatePostPolicy()
        return null;
    }

    @Override
    public OssCallbackResult callback(HttpServletRequest request) {

        return null;
    }
}
