package com.mt.demo.redis.entity;

import lombok.Data;
import org.springframework.cache.annotation.CacheConfig;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * RoleEntity
 *
 * @author MT.LUO
 * 2018/8/6 22:34
 * @Description:
 */
@Data
@Entity
@Table(name = "role_tb")
@CacheConfig(cacheNames = "role")
public class RoleEntity extends BaseEntity<RoleEntity, Long> {
    protected String roleName;
}
