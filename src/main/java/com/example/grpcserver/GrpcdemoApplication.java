package com.example.grpcserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
public class GrpcdemoApplication {

    @Autowired
    private GrpcInitialization grpcInitialization;

    public static void main(String[] args) {
        SpringApplication.run(GrpcdemoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initGrpc() throws IOException {
        grpcInitialization.start();
    }

}
