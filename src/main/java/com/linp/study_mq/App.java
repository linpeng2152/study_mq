package com.linp.study_mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

/**
 * @Author : linpeng
 * ON 2020-10-09
 * used for:
 */
@SpringBootApplication
public class App {

    public static void main(String[] args){
        SpringApplication app = new SpringApplication(App.class);
        Environment env = app.run(args).getEnvironment();
    }
}
