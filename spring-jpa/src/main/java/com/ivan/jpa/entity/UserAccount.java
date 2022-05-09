package com.ivan.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户账户
 *
 * @author WuBing
 * @date 2022-05-09 8:27
 */
@Data
@Entity(name = "user_account")
public class UserAccount {

    @Id
    private Integer userId;
    private Integer balance;
    private Date updateTime;

}
