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
    public String remove(@RequestParam("id")Long id,Model model){
        if(adService.delete(id)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.REMOVE_FAIL);
        }
        return "forward:/ad";
    }

    @RequestMapping("/addInit")
    public String addInit(){
        return "/content/adAdd";
    }

    @RequestMapping("/modifyInit")
    public String modifyInit(Model model, @RequestParam("id")Long id){
        model.addAttribute("modifyObj", adService.getById(id));
        return "/content/adModify";
    }

    /**
     * 修改
     */
    @RequestMapping("/modify")
    public String modify(Model model, AdDto adDto) {
        model.addAttribute("modifyObj", adDto);
        if (adService.modify(adDto)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_FAIL);
        }
        return "/content/adModify";
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
