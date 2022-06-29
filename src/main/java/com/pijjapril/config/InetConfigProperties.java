package com.pijjapril.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
public class InetConfigProperties {
    private InetAddress inetAddress;

    @Bean
    public String getHostName() {
        return inetAddress.getHostName();
    }

    @Bean
    public String getHostAddress() {
        return inetAddress.getHostAddress();
    }
}
