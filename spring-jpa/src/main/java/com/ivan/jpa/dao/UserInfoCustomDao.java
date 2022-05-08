package com.ivan.jpa.dao;

import com.ivan.jpa.entity.UserInfo;
import com.ivan.jpa.vo.UserInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * userInfo数据库操作：使用自定义sql
 *
 * @author WuBing
 * @date 2022-05-08 17:36
 */
@Repository
public interface UserInfoCustomDao extends JpaRepository<UserInfo, Object> {

    @Query("select u.id,u.username,u.age,u.loginTime,u.registerTime from user_info u where u.id = ?1")
    List<Object[]> findSpecifyColumns(long id);

    @Query("select u from user_info u where u.id = ?1")
    UserInfo findById(long id);

//    @Query(value = "select * from user_info where id = ?1") //直接编译报错
//    UserInfo findById2(long id);

    @Query("select u from #{#entityName} u where u.id = ?1")
        //@Entity(name = "user_info")。那么 #{#entityName} 对应的就是 user_info 表。
    UserInfo findById3(long id);

    @Query(value = "select u.* from user_info u where u.id = ?1", nativeQuery = true)
    UserInfo findById4(long id);

    @Query("select u from user_info u where u.id = :id")
    UserInfo findById5(@Param("id") long id);

//    @Query(value = "select new com.ivan.jpa.entity.UserInfo(u.id,u.username,u.age,u.loginTime,u.registerTime) from UserInfo u where u.id = :id")
//    List<UserInfo> findById6(@Param("id") long id);

//    @Query(value = "select u.id,u.username,u.age from user_info u where u.id = ?1", nativeQuery = true)
//    Map<String, Object> findById7(long id);

    @Query(value = "select a.* from user_info a where a.age >= :age",
            countQuery = "select count(a.id) from user_info a where a.age >= :age", nativeQuery = true)
    Page<UserInfo> findByPage(@Param("age") int age, Pageable pageable);

    @Query(value = "select a.id, a.userId, a.username, a.age from user_info a where a.age >= :age",
            countQuery = "select count(a.id) from user_info a where a.age >= :age", nativeQuery = true)
    Page<UserInfoVo> findByPage2(@Param("age") int age, Pageable pageable);

    @Modifying
    @Query(value = "update user_info u set u.age = :age where u.id = :id")
    int updateUserAge(@Param("age") int age, @Param("id") long id);

}
