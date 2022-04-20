package com.ivan.service;

import com.ivan.dao.UserDao;
import com.ivan.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: WB
 * @version: v1.0
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> findBySex(Boolean isBoy) {
        return userDao.findBySex(isBoy);
    }


}
