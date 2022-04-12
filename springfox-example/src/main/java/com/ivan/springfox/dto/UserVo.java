package com.ivan.springfox.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: WB
 * @version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("个人信息")
public class UserVo {

    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("姓名")
    private String username;

}
