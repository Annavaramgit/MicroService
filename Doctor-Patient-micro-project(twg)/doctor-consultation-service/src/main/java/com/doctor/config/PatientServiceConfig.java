package com.doctor.config;

import com.doctor.open_feign.OpenFeign_interface;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
    public class PatientServiceConfig {
    @Value("${patient-service.get-patient-url}")
    private String getPatientUrl;

    @Value("${patient-service.save-patient-url}")
    private String savePatientUrl;

    @Bean
    public OpenFeign_interface openFeignInterface() {
        return Feign.builder()
                .target(OpenFeign_interface.class, getPatientUrl);
    }
    @Bean
    public OpenFeign_interface openFeignSavePatientInterface() {
        return Feign.builder()
                .encoder(feignEncoder())
                .decoder(feignDecoder())
                .target(OpenFeign_interface.class, savePatientUrl);
    }


    @Bean
    public Encoder feignEncoder() {
        return new JacksonEncoder();
    }

    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder();
    }
}
