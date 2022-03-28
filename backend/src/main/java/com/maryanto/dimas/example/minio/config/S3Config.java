package com.maryanto.dimas.example.minio.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    private final String endpointUrl;
    private final Integer endpointPort;
    private final Boolean endpointSecure;
    private final String accessKey;
    private final String secretKey;
    private final String endpointRegion;

    public S3Config(@Value("${minio.endpoint.url}") String endpointUrl,
                    @Value("${minio.endpoint.port}") Integer endpointPort,
                    @Value("${minio.endpoint.secure}") Boolean endpointSecure,
                    @Value("${minio.endpoint.region}") String endpointRegion,
                    @Value("${minio.credential.access_key}") String accessKey,
                    @Value("${minio.credential.secret_key}") String secretKey) {
        this.endpointUrl = endpointUrl;
        this.endpointPort = endpointPort;
        this.endpointSecure = endpointSecure;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.endpointRegion = endpointRegion;
    }

    @Bean
    public MinioClient client() {
        MinioClient build = MinioClient.builder()
                .endpoint(endpointUrl, endpointPort, endpointSecure)
                .credentials(accessKey, secretKey)
//                .region(endpointRegion)
                .build();
        return build;
    }
}
