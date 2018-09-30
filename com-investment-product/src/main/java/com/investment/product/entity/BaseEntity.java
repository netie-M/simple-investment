package com.investment.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {
    @CreatedBy
    @JsonIgnore
    private String createdByUser;
    @CreatedDate
    @JsonIgnore
    private Date creationDate;
    @LastModifiedBy
    @JsonIgnore
    private String modifiedByUser;
    @LastModifiedDate
    @JsonIgnore
    private Date modificationDate;
    @Version
    @JsonIgnore
    private Long version;
}
