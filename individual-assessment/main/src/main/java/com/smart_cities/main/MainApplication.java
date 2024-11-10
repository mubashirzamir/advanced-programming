package com.smart_cities.main;

import com.smart_cities.main.service.CitizenLauncher;
import com.smart_cities.main.service.ProviderLauncher;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        new ProviderLauncher().launch();
        new CitizenLauncher().launch();
    }
}
