package com.ivan.jpa.service;

import com.ivan.jpa.dao.UserInfoDefDao;
import com.ivan.jpa.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author WuBing
 * @date 2022-05-08 18:14
 */
@Service
public class UserService {

    @Autowired
    private UserInfoDefDao userInfoDao;

    public UserInfo findById(Long id) {
        return userInfoDao.findById(id).orElse(null);
    }
}
