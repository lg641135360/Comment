package com.lg.dao;

import com.lg.bean.Ad;

import java.util.List;

public interface AdDao {
    int insert(Ad ad);  //返回修改了多少条数据

    /**
     * 根据条件查询
     * @param condition 封装了条件的Ad实体
     * @return 返回查询出来的Ad实体 list集合
     */
    List<Ad> selectByPage(Ad condition);
}
