package com.lg.service;

import com.lg.dto.AdDto;

import java.util.List;

public interface AdService {

    /**
     * 新增广告
     * @param adDto
     * @return 是否新增成功：true-成功; false-失败
     */
    boolean add(AdDto adDto);

    List<AdDto> searchByPage(AdDto adDto);
}
