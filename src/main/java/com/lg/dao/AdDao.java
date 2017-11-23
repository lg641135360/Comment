package com.lg.dao;

import com.lg.bean.Ad;

import java.util.List;

public interface AdDao {
    /**
     * 新增
     * @param ad 广告表对象
     * @return 影响行数
     */
    int insert(Ad ad);  //返回修改了多少条数据

    /**
     * 根据条件查询
     * @param condition 封装了条件的Ad实体
     * @return 返回查询出来的Ad实体 list集合
     */
    List<Ad> selectByPage(Ad condition);

    /**
     * 根据主键删除
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据主键查询广告对象
     * @param id 主键值
     * @return 广告对象
     */
    Ad selectById(Long id);

    /**
     * 根据主键修改
     * @param ad 待修改的广告对象
     * @return 影响行数
     */
    int update(Ad ad);
}
