package com.mt.demo.redis.runner;

import com.github.wenhao.jpa.Specifications;
import com.mt.demo.redis.config.MyRedisCacheManager;
import com.mt.demo.redis.entity.UserEntity;
import com.mt.demo.redis.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * MySchedule
 *
 * @author MT.LUO
 * 2018/8/11 18:12
 * @Description:
 */
@Slf4j
@Component
@EnableScheduling
public class MySchedule {

    @Autowired
    private MyRedisCacheManager myRedisCacheManager;

    @Autowired
    private UserRepository userRepository;

    private long timeout = 0;

    @Scheduled(cron = "0/40 * * * * ?")
    public void refreshUserList() {

        log.info("refresh time : {}", System.currentTimeMillis());
        Specification<UserEntity> specification = Specifications.<UserEntity>and().eq("deleted", false).build();
        Pageable pageable = PageRequest.of(0, 10);
        myRedisCacheManager.insert("test::class com.mt.demo.redis.service.UserService_10_0", userRepository.findAll
                (specification, pageable), timeout, TimeUnit.SECONDS);
    }
}
