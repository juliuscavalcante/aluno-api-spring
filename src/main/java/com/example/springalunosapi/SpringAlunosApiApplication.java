package com.example.springalunosapi;

import com.example.springalunosapi.config.ApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableConfigurationProperties(ApiProperty.class)
public class SpringAlunosApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAlunosApiApplication.class, args);
    }

}
