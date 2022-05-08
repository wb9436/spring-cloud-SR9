package com.ivan.jpa.dao;

import com.ivan.jpa.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * userInfo数据库操作：使用默认sql
 *
 * @author WuBing
 * @date 2022-05-08 17:36
 */
@Repository
public interface UserInfoDefDao extends JpaRepository<UserInfo, Long> {

}
