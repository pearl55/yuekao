package com.bw.gaoyongpei.controller;


import com.bw.gaoyongpei.bean.UserTest;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserTestSwagger {
    static Map<Integer, UserTest> users = Collections.synchronizedMap(new HashMap<Integer, UserTest>());

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public List<UserTest> getUserList() {
        List<UserTest> r = new ArrayList<UserTest>(users.values());
        return r;
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postUser(@RequestBody UserTest user) {
        users.put(user.getId(), user);
        return "success";
    }
}

