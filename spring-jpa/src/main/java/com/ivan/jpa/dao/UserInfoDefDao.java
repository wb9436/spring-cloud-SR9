package com.ivan.jpa.dao;

import com.ivan.jpa.entity.UserInfo;
import com.ivan.jpa.vo.UserInfoVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * userInfo数据库操作：使用默认sql
 *
 * @author WuBing
 * @date 2022-05-08 17:36
 */
@Repository
public interface UserInfoDefDao extends JpaRepository<UserInfo, Long> {

    @Query(value = "select a.userId,a.username,a.age from user_info a where a.id = ?1", nativeQuery = true)
    Map<String, Object> findByIdReturnMap(long id);

    @Query(value = "select a.userId,a.username,a.age from user_info a where a.id = ?1", nativeQuery = true)
    UserInfoVo findByIdReturnDto(long id);

    @Query(value = "select a.userId,a.username,a.age from user_info a", nativeQuery = true)
    List<UserInfoVo> findAll3();
}
