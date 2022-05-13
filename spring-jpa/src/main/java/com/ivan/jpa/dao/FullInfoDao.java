package com.ivan.jpa.dao;

import com.ivan.jpa.entity.FullInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * dao测试
 *
 * @author WuBing
 * @date 2022-05-13 10:00
 */
@Repository
public interface FullInfoDao extends JpaRepository<FullInfo, Integer> {

    /**
     * FullInfo 需要使用@Table注解（并指定表名）
     * @param userId
     * @return
     */
    @Query(value = "from FullInfo where userId = ?1")
    public FullInfo findInfoByUserId(Integer userId);

}
