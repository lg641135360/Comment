package com.lg.service.impl;

import com.lg.bean.Ad;
import com.lg.dao.AdDao;
import com.lg.dto.AdDto;
import com.lg.service.AdService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("adService")
public class AdServiceImpl implements AdService {

    @Autowired
    private AdDao adDao;

    @Value("${adImage.savePath}")
    private String adImageSavePath;

    @Value("${adImage.url}")
    private String adImageUrl;

    //TODO 可以改成获取失败详细原因
    public boolean add(AdDto adDto) {
        Ad ad = new Ad();
        ad.setTitle(adDto.getTitle());
        ad.setLink(adDto.getLink());
        ad.setWeight(adDto.getWeight());
        if(adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + adDto.getImgFile().getOriginalFilename();
            File file = new File(adImageSavePath + fileName );
            File fileFolder = new File(adImageSavePath);
            if (!fileFolder.exists()){
                fileFolder.mkdirs();
            }
            try {
                adDto.getImgFile().transferTo(file);
                ad.setImgFileName(fileName);
                adDao.insert(ad);
                return true;
            } catch (IOException e) {
                //TODO 需要添加日志
                return false;
            }
        } else {
            return false;
        }
    }

    public List<AdDto> searchByPage(AdDto adDto) {
        List<AdDto> result = new ArrayList<AdDto>();
        Ad condition = new Ad();
        BeanUtils.copyProperties(adDto,condition);
        List<Ad> adList = adDao.selectByPage(condition);
        for (Ad ad : adList) {
            AdDto adDtoTemp = new AdDto();
            result.add(adDtoTemp);
            adDtoTemp.setImg(adImageUrl + ad.getImgFileName());
            BeanUtils.copyProperties(ad,adDtoTemp);
        }
        return result;
    }

    public boolean delete(Long id) {
        if (adDao.deleteById(id) > 0)
            return true;
        else
            return false;
    }
}
