package com.meitu.web;

import com.meitu.hbase.HBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/near")
public class ProductNearController {

    @Autowired
    private HBaseService hBaseService;

    /**
     * 计算
     * @return
     */
    @RequestMapping("/compute")
    public String compute() {
        return "product/near/list";
    }

}
