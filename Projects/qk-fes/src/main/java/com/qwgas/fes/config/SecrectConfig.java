package com.qwgas.fes.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class SecrectConfig {

    @Value(value = "${appId}")
    private String appId;

    @Value(value = "${appSecrect}")
    private String appSecrect;


    /**
     * 应用唯一标识id
     */
    @Value(value = "${api.serviceId}")
    private String serviceId;
    /**
     * 应用密钥
     */
    @Value(value = "${api.serviceSecret}")
    private String serviceSecret;
}
