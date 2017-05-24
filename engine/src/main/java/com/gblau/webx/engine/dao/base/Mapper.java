package com.gblau.webx.engine.dao.base;

import java.util.List;

/**
 * @author gblau
 * @date 2017-04-09
 */
public interface Mapper<T> {
    int deleteByPrimaryKey(String id);
    int insert(T record);
    T selectByPrimaryKey(String id);
    List<T> selectAllElements();
    int updateByPrimaryKey(T record);
}