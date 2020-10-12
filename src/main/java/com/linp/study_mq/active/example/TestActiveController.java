package com.linp.study_mq.active.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : linpeng
 * ON 2020-10-10
 * used for:
 */

@RestController
@RequestMapping("/active-mq")
public class TestActiveController {

    @Autowired
    private PushService pushService;

    @RequestMapping("/send")
    public String sendMsg(String content){
        pushService.push(content);
        return "success";
    }

}
