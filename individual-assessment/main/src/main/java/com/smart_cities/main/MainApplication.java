package com.smart_cities.main;

import com.smart_cities.main.service.CitizenLauncher;
import com.smart_cities.main.service.ProviderLauncher;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) throws IOException {
        new ProviderLauncher().launch();
        new CitizenLauncher().launch();
    }
}
