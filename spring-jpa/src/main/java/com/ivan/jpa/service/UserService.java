package com.ivan.jpa.service;

import com.ivan.jpa.dao.UserAccountDao;
import com.ivan.jpa.dao.UserInfoDefDao;
import com.ivan.jpa.entity.UserAccount;
import com.ivan.jpa.entity.UserInfo;
import com.ivan.jpa.vo.UserInfoVo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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
    @Autowired
    private UserAccountDao userAccountDao;

    public UserInfo findById(long id) {
//        return userInfoDao.findById(id).orElse(null);
        return userInfoDao.findById1(id);
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

    public List<UserInfo> findAll1() {
        return userInfoDao.findAll();
    }

    public List<UserInfoVo> findAll3() {
        return userInfoDao.findAll3();
    }

    public UserAccount getUserBalance(int userId) {
        return userAccountDao.findById(userId).orElse(null);
    }

    public UserInfoVo getUserBaseInfo(int userId) {
        return userInfoDao.getUserBaseInfo(userId);
    }

    public List<UserInfoVo> getPageList(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return userInfoDao.getPageList(pageable);
    }
}
