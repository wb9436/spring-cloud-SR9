package com.ivan.springfox.controller;

import com.ivan.springfox.dto.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: WB
 * @version: v1.0
 */
@Controller
@Api(tags = "用户请求控制器")
public class UserController {

    // 向用户登录页面跳转
    @ApiOperation("向用户登录页面跳转")
    @GetMapping("/userLogin")
    public String toLoginPage(Model model) {
        model.addAttribute("hello", "Hello");
        return "login";
    }

    /**
     * @ApiImplicitParams: 用在请求的方法上，表示一组参数说明
     * <p>
     * @ApiImplicitParam: 用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
     * -- name：参数名
     * -- value：参数的汉字说明、解释
     * -- required：参数是否必须传
     * -- paramType：参数放在哪个地方
     * -- --  header --> 请求参数的获取：@RequestHeader
     * -- --  query --> 请求参数的获取：@RequestParam
     * -- --  path（用于restful接口）–> 请求参数的获取：@PathVariable
     * -- --  body（不常用）
     * -- --  form（不常用）
     * -- dataType：参数类型，默认String，其它值dataType=“Integer”
     * -- defaultValue：参数的默认值
     */
    @ApiOperation("获取用户信息")
    @ApiImplicitParam(name = "Id", value = "")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public UserVo getUser(Long Id) {
        UserVo userVo1 = new UserVo(10001L, "Tom");
        UserVo userVo2 = new UserVo(10002L, "Tom");
        Map<Long, UserVo> map = new HashMap<>();
        map.put(userVo1.getUserId(), userVo1);
        map.put(userVo2.getUserId(), userVo2);
        return map.get(Id);
    }

    @ApiIgnore
    @RequestMapping(value = "/ignored")
    @ResponseBody
    public String ignored() {
        return "ignored";
    }
}


