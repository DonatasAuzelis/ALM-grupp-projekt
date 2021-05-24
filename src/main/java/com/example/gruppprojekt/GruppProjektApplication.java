package com.example.gruppprojekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;


@SpringBootApplication
@EnableMongoAuditing
public class GruppProjektApplication {

    public static void main(String[] args) {
        //System.setProperty("user.timezone", "Europe/Stockholm");
        SpringApplication.run(GruppProjektApplication.class, args);
    }

}
