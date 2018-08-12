package com.mt.demo.redis.service;

import com.github.wenhao.jpa.Specifications;
import com.mt.demo.redis.repository.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * BaseService
 *
 * @author MT.LUO
 * 2018/8/6 22:52
 * @Description:
 */
@Slf4j
@EnableCaching
@CacheConfig(cacheNames = "baseentity")
public class BaseService<T, ID extends Serializable> {

    @Autowired
    protected BaseRepository<T, ID> baseRepository;

    @CachePut(key = "#root.targetClass + '_' + #entity.id")
    public void save(T entity) {
        baseRepository.save(entity);
    }

    @CacheEvict(key = "#root.targetClass + '_' + #id")
    public void del(ID id) {
        baseRepository.deleteById(id);
    }

    @CacheEvict(key = "#root.targetClass + '_' + #id")
    public void setDeletedTrue(ID id) {
        log.info("eeee: {}", baseRepository.setDeletedTrue(id));
    }

    @Cacheable(key = "#root.targetClass + '_' + #id")
    public Optional<T> findOne(ID id) {
        return baseRepository.findById(id);
    }

    public List<T> findAll() {
        Specification<T> specification = Specifications.<T>and().eq("deleted", false).build();
        return baseRepository.findAll(specification);
    }

    @Cacheable(value = "test", key = "#root.targetClass + '_' + #p0 + '_' + #p1")
    public Page<T> findAll(int pageSize, int pageNumber) {
        log.info("page: {}, number: {}", pageSize, pageNumber);
        Specification<T> specification = Specifications.<T>and().eq("deleted", false).build();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return baseRepository.findAll(specification, pageable);
    }

}
