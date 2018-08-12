package com.mt.demo.redis.entity;

import lombok.Data;
import org.springframework.cache.annotation.CacheConfig;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UserEntity
 *
 * @author MT.LUO
 * 2018/8/6 22:32
 * @Description:
 */
@Data
@Entity
@Table(name = "user_tb")
@CacheConfig(cacheNames = "user")
public class UserEntity extends BaseEntity<UserEntity, Long> {

    protected String userName;
}
