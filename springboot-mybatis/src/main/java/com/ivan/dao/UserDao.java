package com.ivan.dao;

import com.ivan.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: WB
 * @version: v1.0
 */
@Repository
public interface UserDao {

    List<User> findBySex(@Param("isBoy") Boolean isBoy);
}
