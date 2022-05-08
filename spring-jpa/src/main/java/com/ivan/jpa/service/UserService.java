package com.ivan.jpa.service;

import com.ivan.jpa.dao.UserInfoDefDao;
import com.ivan.jpa.entity.UserInfo;
import com.ivan.jpa.vo.UserInfoVo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    public UserInfo findById(long id) {
        return userInfoDao.findById(id).orElse(null);
    }

    public UserInfoVo findType2ById(long id) {
        Map<String, Object> map = userInfoDao.findByIdReturnMap(id);
        if (map != null && !map.isEmpty()) {
            try {
                UserInfoVo userInfoVo = new UserInfoVo();
                BeanUtils.copyProperties(userInfoVo, map);
                return userInfoVo;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public UserInfoVo findType3ById(long id) {
        return userInfoDao.findByIdReturnDto(id);
    }
}
