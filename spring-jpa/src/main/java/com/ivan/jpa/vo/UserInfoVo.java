package com.ivan.jpa.vo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户表对象
 *
 * @author WuBing
 * @date 2022-05-08 17:25
 */
public interface UserInfoVo {
    long getId();
    int getUserId();
    String getUsername();
    int getAge();

}
