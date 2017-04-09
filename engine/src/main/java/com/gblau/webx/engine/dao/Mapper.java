package com.gblau.webx.engine.dao;

import java.util.List;

/**
 * @author gblau
 * @date 2017-04-09
 */
public interface Mapper<T> {
    int deleteByPrimaryKey(int id);
    int insert(T record);
    T selectByPrimaryKey(int id);
    List<T> selectAllElements();
    int updateByPrimaryKey(T record);
}