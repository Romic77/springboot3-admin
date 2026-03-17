package com.meow.admin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 文件上传地址
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "meow.file.upload")
public class FileUploadConfig {

    /**
     * aws-s3 远程服务地址
     */
    private String url;
} 