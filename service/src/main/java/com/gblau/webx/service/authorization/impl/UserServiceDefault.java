package com.gblau.webx.service.authorization.impl;

import com.gblau.webx.engine.dao.authorization.UserMapper;
import com.gblau.webx.engine.dao.base.Mapper;
import com.gblau.webx.model.po.authority.User;
import com.gblau.webx.service.authorization.UserService;
import com.gblau.webx.service.base.impl.BaseServiceDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gblau
 * @date 2017-05-22
 */
@Service
public class UserServiceDefault extends BaseServiceDefault<User> implements UserService {
    @Autowired
    private UserMapper dao;

    /**
     * base类并不提供具体的dao实现，所以需要设置实际运行的Dao类。
     * 业务类实现该方法并调用 {@link com.gblau.webx.service.base.BaseService#setBaseMapper(Mapper)}
     * 并设置@Autowired或者@Resources自动注入
     * @author gblau
     */
    @Autowired
    @Override
    public void setMapper() {
        super.setBaseMapper(dao);
    }
}
