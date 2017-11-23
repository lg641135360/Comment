package com.lg.controller.api;

import com.lg.dto.AdDto;
import com.lg.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Create by MIO on 2017/11/14 14:57
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AdService adService;

    @Value("${ad.number}")
    private int adNumber;

    @RequestMapping(value = "/homead",method = RequestMethod.GET)
    public List<AdDto> homedad(){
        AdDto adDto = new AdDto();
        adDto.getPage().setPageNumber(adNumber);
        return adService.searchByPage(adDto);
    }

}
