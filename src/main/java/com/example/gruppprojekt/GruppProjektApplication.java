package com.example.gruppprojekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class GruppProjektApplication {

    public static void main(String[] args) {
        SpringApplication.run(GruppProjektApplication.class, args);
    }

}
