package com.mt.demo.redis.controller;

import com.mt.demo.redis.entity.BaseEntity;
import com.mt.demo.redis.runner.MySchedule;
import com.mt.demo.redis.service.BaseService;
import com.mt.demo.redis.utils.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * BaseController
 *
 * @author MT.LUO
 * 2018/8/6 22:37
 * @Description:
 */
@Slf4j
public class BaseController<S extends BaseService<T, ID>, T extends BaseEntity<T, ID>, ID extends Serializable> {
    @Autowired
    protected S baseManager;

    @Autowired
    private MySchedule mySchedule;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultModel getList(@RequestParam int pageSize, @RequestParam int pageNumber) {
        log.info("BaseController list");
        if (pageSize > 0) {

            Page<T> page = baseManager.findAll(pageSize, pageNumber);
            return ResultModel.ok(page);

        } else {
            List<T> entities = baseManager.findAll();
            return ResultModel.ok(entities);
        }
    }

    @RequestMapping(value = "/action", method = RequestMethod.POST)
    public ResultModel add(@Valid T entity) {
        log.info("BaseController action post");

        log.info(entity.toString());

        baseManager.save(entity);
        return ResultModel.ok();
    }
    @RequestMapping(value = "/action/{id}", method = RequestMethod.GET)
    public ResultModel findOne(@PathVariable ID id) {
        log.info("BaseController action findOne");

        log.info("BaseController id{}", id);

        return ResultModel.ok(baseManager.findOne(id));
    }

    @RequestMapping(value = "/action/{id}", method = RequestMethod.POST)
    public ResultModel update(@Valid T entity, @PathVariable ID id) {
        log.info("BaseController action PUT");

        log.info("BaseController entity{}", entity.toString());
        // 防止更新不存在的数据
        Optional<T> optional = baseManager.findOne(id);
        if (optional.isPresent()) {
            T old = optional.get();
            entity.setId(id);
            entity.setDeleted(old.isDeleted());
            baseManager.save(old);
        }
        return ResultModel.ok();
    }

    @RequestMapping(value = "/action/{id}", method = RequestMethod.DELETE)
    public ResultModel delete(@PathVariable ID id) {
        log.info("BaseController action DELETE");
        baseManager.setDeletedTrue(id);
        return ResultModel.ok();
    }

}
