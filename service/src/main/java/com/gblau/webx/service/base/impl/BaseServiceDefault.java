package com.gblau.webx.service.base.impl;

import com.gblau.webx.engine.dao.base.Mapper;
import com.gblau.webx.service.base.BaseService;

/**
 * @author gblau
 * @date 2017-04-09
 */
public abstract class BaseServiceDefault<T> implements BaseService<T> {
    private Mapper<T> mapper;

    /**
     * base类并不提供具体的dao实现，所以需要设置实际运行的Dao类。
     * @param dao
     */
    @Override
    public void setBaseMapper(Mapper<T> dao) {
        this.mapper = dao;
    }

    /**
     * 通过主键删除数据，支持Mysql int类型主键删除
     *
     * @param id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(String id) {
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 插入数据
     *
     * @param record
     * @return
     */
    @Override
    public int insert(T record) {
        return mapper.insert(record);
    }

    /**
     * 插入一个bean对象中不为null的数据。
     *
     * @param record
     * @return
     */
    @Override
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    /**
     * 通过主键查找一个数据，支持Mysql int类型主键查找。
     *
     * @param id
     * @return
     */
    @Override
    public T findByPrimaryKey(String id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 通过record中的ID更新指定字段, 具体须有具体的Mapper实现
     *
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    /**
     * 更新指定数据，即对象中不为null的数据。
     *
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }
}
