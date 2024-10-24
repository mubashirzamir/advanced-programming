package com.smart_cities.electricity_provider;


import com.smart_cities.electricity_provider.model.Consumption;
import com.smart_cities.electricity_provider.repository.ConsumptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadElectricityProviderData {
    private static final Logger log = LoggerFactory.getLogger(LoadElectricityProviderData.class);

    @Bean
    CommandLineRunner initDatabase(ConsumptionRepository repository) {
        return args -> {
            log.info("Preloading {}", repository.save(new Consumption(1L, null, 5)));
            log.info("Preloading {}", repository.save(new Consumption(null, 1L, 7)));
        };
    }
}