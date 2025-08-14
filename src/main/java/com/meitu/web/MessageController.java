package com.meitu.web;

import cn.hutool.json.JSONUtil;
import com.meitu.domain.LogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;


    @PostMapping("/send")
    public String send(@RequestBody LogEntity logEntity, @RequestParam("topic") String topic) {
        messageService.sendMessage(topic, JSONUtil.toJsonStr(logEntity));
        return "success";
    }

}
