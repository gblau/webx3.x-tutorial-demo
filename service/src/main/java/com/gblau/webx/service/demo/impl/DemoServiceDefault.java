package com.gblau.webx.service.demo.impl;

import com.gblau.webx.engine.dao.base.Mapper;
import com.gblau.webx.engine.dao.demo.DemoMapper;
import com.gblau.webx.model.po.demo.Demo;
import com.gblau.webx.service.base.BaseService;
import com.gblau.webx.service.base.impl.BaseServiceDefault;
import com.gblau.webx.service.demo.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gblau
 * @date 2017-05-24
 */
@Service
public class DemoServiceDefault extends BaseServiceDefault<Demo> implements DemoService {
    @Autowired
    private DemoMapper dao;

    /**
     * base类并不提供具体的dao实现，所以需要设置实际运行的Dao类。
     * 业务类实现该方法并调用 {@link BaseService#setBaseMapper(Mapper)}
     * 并设置@Autowired或者@Resources自动注入
     *
     * @author gblau
     */
    @Override
    public void setMapper() {
        setBaseMapper(dao);
    }

}
