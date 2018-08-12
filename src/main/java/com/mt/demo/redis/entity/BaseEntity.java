package com.mt.demo.redis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * BaseEntity
 *
 * @author MT.LUO
 * 2018/8/6 22:31
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class BaseEntity<T extends BaseEntity, ID> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected ID id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "gmt_create", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date gmtCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "gmt_update", nullable = true, insertable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date gmtUpdate;

    /** true ---deleted ; false -- exist */
    // 虚假删除，deleted为true表示删除了
    @Column(name = "deleted", length = 1)
    protected boolean deleted = false;
}