package com.lg.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by MIO on 2017/11/14 15:33
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping
    public String init(){
        return "/system/index";
    }
}
