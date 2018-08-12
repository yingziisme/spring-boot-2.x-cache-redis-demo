package com.mt.demo.redis.controller;

import com.mt.demo.redis.entity.RoleEntity;
import com.mt.demo.redis.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RoleController
 *
 * @author MT.LUO
 * 2018/8/6 23:06
 * @Description:
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController extends BaseController<RoleService, RoleEntity, Long> {
}
