package com.meitu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;


    @PostMapping("/send")
    public String send(@RequestParam("message") String message,@RequestParam("topic") String topic) {
        messageService.sendMessage(topic, message);
        return "success";
    }

}
