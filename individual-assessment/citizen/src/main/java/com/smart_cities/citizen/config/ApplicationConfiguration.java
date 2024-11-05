package com.smart_cities.citizen.config;

import com.smart_cities.citizen.contracts.GeneratesReadings;
import com.smart_cities.citizen.entity.Citizen;
import com.smart_cities.citizen.entity.SmartMeter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class ApplicationConfiguration {
    @Value("${type}")
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public GeneratesReadings generatesReadings() {
        if (this.getType().equals("smart_meter")) {
            return new SmartMeter();
        }

        return new Citizen();
    }
}
