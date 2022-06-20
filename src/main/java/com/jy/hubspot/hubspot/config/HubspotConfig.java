package com.jy.hubspot.hubspot.config;

import com.jy.hubspot.hubspot.HubspotApplication;
import com.jy.hubspot.hubspot.service.DemoService;
import com.jy.hubspot.hubspot.service.HubspotApiService;
import com.jy.hubspot.hubspot.service.impl.DemoServiceImpl;
import com.jy.hubspot.hubspot.service.impl.HubspotApiServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = HubspotApplication.class)
public class HubspotConfig {

    @Bean
    public DemoService demoService(HubspotApiService hubspotApiService) { return new DemoServiceImpl(hubspotApiService); }

    @Bean
    public HubspotApiService hubspotApiService() { return new HubspotApiServiceImpl(); }

}