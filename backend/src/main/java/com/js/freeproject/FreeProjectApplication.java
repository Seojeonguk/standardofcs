package com.js.freeproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class FreeProjectApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FreeProjectApplication.class);
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
    }
}
