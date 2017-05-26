package com.gblau.webx.engine.dao.base;

/**
 * @author gblau
 * @date 2017-04-09
 */
public interface Mapper<T> {
    int insert(T record);

    int updateByPrimaryKey(T record);

    int deleteByPrimaryKey(String id);

    int insertSelective(T record);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T record);

}