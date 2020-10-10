package com.linp.study_mq.rabbit.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : linpeng
 * ON 2020-10-10
 * used for:
 */
@RestController
@RequestMapping("/rabbit-mq")
public class TestController {

    @Autowired
    private NoticeMqService mqService;

    @RequestMapping("/send")
    public String testSend(String msg){
        mqService.sendMessage(msg);
        return "success";
    }

}
