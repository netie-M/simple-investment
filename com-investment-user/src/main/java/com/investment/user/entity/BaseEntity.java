package com.investment.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    @JsonIgnore private String createBy;
    @JsonIgnore private Date createDate;
    @JsonIgnore private String modifyBy;
    @JsonIgnore private Date modifyDate;
    @JsonIgnore private String version;
}
