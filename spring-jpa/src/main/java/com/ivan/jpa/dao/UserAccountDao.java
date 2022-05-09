package com.ivan.jpa.dao;

import com.ivan.jpa.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户账户相关数据库操作
 *
 * @author WuBing
 * @date 2022-05-09 8:30
 */
@Repository
public interface UserAccountDao extends JpaRepository<UserAccount, Integer> {



}
