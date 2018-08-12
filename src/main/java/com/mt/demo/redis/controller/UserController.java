package com.mt.demo.redis.controller;

import com.mt.demo.redis.entity.UserEntity;
import com.mt.demo.redis.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author MT.LUO
 * 2018/8/6 23:05
 * @Description:
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<UserService, UserEntity, Long> {
}
