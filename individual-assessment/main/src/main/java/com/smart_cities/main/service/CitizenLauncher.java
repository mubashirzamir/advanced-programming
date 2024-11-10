package com.smart_cities.main.service;

import com.smart_cities.main.contracts.Launcher;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CitizenLauncher implements Launcher {
    @Override
    public void launch() {
        for (int i = 0; i < 10; i++) {
            String port = String.valueOf(10081 + i);
            CitizenLauncher.launch(i, port);
        }
    }

    private static void launch(int id, String port) {
        String command = "java -jar ../citizen/target/citizen-0.0.1-SNAPSHOT.jar --server.port=" + port;
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));

        String type = CitizenLauncher.deriveType(id);
        String provider = CitizenLauncher.deriveProvider(id);

        processBuilder.environment().put("id", String.valueOf(id));
        processBuilder.environment().put("type", type);
        processBuilder.environment().put("provider_id", provider);

        try {
            processBuilder.start();
            System.out.println("Started Citizen Instance" +
                    "\nID: " + id +
                    "\nPort:" + port +
                    "\nType: " + type +
                    "\nProvider: " + provider
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String deriveType(int id) {
        return id % 2 == 0 ? "citizen" : "smart_meter";
    }

    private static String deriveProvider(int id) {
        return switch (id % 3) {
            case 1 -> "1";
            case 2 -> "2";
            default -> "3";
        };
    }
}
