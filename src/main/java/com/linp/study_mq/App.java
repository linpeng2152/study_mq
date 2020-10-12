package com.linp.study_mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.EnableJms;

/**
 * @Author : linpeng
 * ON 2020-10-09
 * used for:
 */
@SpringBootApplication
@ServletComponentScan
@EnableJms
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
