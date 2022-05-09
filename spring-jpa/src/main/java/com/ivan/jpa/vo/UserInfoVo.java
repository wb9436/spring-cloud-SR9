package com.ivan.jpa.vo;

import com.ivan.jpa.jpa.JpaQueryDto;
import lombok.Data;

/**
 * 查询返回对象
 *
 * @author WuBing
 * @date 2022-05-08 17:25
 */
@Data
public class UserInfoVo implements JpaQueryDto {

    private Integer userId;
    private String username;
    private Integer age;
    private Integer balance;

}
