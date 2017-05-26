package com.gblau.webx.service.base;

import com.gblau.webx.engine.dao.base.Mapper;

/**
 * 通用的service处理类，满足普通的增删查改操作。
 * @author gblau
 * @date 2017-04-09
 */
public interface BaseService<T> {

    /**
     * base类并不提供具体的dao实现，所以需要设置实际运行的Dao类。
     * 业务类实现该方法并调用 {@link com.gblau.webx.service.base.BaseService#setBaseMapper(Mapper)}
     * 并设置@Autowired或者@Resources自动注入
     * @author gblau
     */
    void setMapper();

    /**
     * base类并不提供具体的dao实现，所以需要设置实际运行的Dao类。
     * @param dao
     */
    public void setBaseMapper(Mapper<T> dao);

    /**
     * 通过主键删除数据，支持Mysql int类型主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入数据
     * @param record
     * @return
     */
    int insert(T record);

    /**
     * 插入一个bean对象中不为null的数据。
     * @param record
     * @return
     */
    int insertSelective(T record);

    /**
     * 通过主键查找一个数据，支持Mysql int类型主键查找。
     * @param id
     * @return
     */
    T findByPrimaryKey(String id);

    /**
     * 通过record中的ID更新指定字段, 具体须有具体的Mapper实现
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);

    /**
     * 更新指定数据，即对象中不为null的数据。
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);
}