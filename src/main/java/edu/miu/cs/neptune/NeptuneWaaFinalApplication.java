package edu.miu.cs.neptune;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NeptuneWaaFinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeptuneWaaFinalApplication.class, args);
    }

}
