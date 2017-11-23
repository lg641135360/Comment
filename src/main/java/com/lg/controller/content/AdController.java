package com.lg.controller.content;

import com.lg.constant.PageCodeEnum;
import com.lg.dto.AdDto;
import com.lg.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Create by MIO on 2017/11/14 15:49
 */
@Controller
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;

    @RequestMapping
    public String init(){
        return "/content/adList";
    }

    @RequestMapping("/search")
    public String search(Model model, AdDto adDto){
        model.addAttribute("list",adService.searchByPage(adDto));
        model.addAttribute("searchParam",adDto);
        return "/content/adList";
    }

    @RequestMapping("/remove")
    public String remove(@RequestParam("id")Long id){
        adService.delete(id);
        return "forward:/ad";
    }

    @RequestMapping("/addInit")
    public String addInit(){
        return "/content/adAdd";
    }

    @RequestMapping("/add")
    public String add(AdDto adDto, Model model){
        if (adService.add(adDto)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
        }
        return "/content/adAdd";
    }
}
