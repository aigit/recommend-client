package com.meitu.web;

import cn.hutool.json.JSONUtil;
import com.meitu.domain.LogEntity;
import com.meitu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductMetricsController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/form")
    public String form(Model model) {
        model.addAttribute("logEntity", new LogEntity());
        return "form";
    }

    @RequestMapping("/submit")
    public String compute(@ModelAttribute("logEntity") LogEntity logEntity, Model model) {
        model.addAttribute("success", "上报成功");
        messageService.sendMessage("product-feature", JSONUtil.toJsonStr(logEntity));
        return "result";
    }

}
