package com.lg.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by MIO on 2017/11/14 15:49
 */
@Controller
@RequestMapping("/ad")
public class AdController {

    @RequestMapping
    public String init(){
        return "/content/adList";
    }

    @RequestMapping("/addInit")
    public String addInit(){
        return "/content/adAdd";
    }
}
