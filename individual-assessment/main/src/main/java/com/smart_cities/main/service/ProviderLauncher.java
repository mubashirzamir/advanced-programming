package com.smart_cities.main.service;

import com.smart_cities.main.contracts.Launcher;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProviderLauncher implements Launcher {
    @Override
    public void launch() {
        for (int i = 0; i < 1; i++) {
            ProviderLauncher.launch(i, String.valueOf(9081 + i));
        }
    }

    private static void launch(int id, String port) {
        String command = "java -jar ../provider/target/provider-0.0.1-SNAPSHOT.jar --server.port=" + port;
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        processBuilder.environment().put("id", String.valueOf(id));

        try {
            processBuilder.start();
            System.out.println("Started Provider Instance" +
                    "\nID: " + id +
                    "\nPort:" + port
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
