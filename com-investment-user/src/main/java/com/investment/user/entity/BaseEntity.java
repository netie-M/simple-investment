package com.investment.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {
    @JsonIgnore @JSONField(serialize = false)private String createBy;
    @JsonIgnore @JSONField(serialize = false)private Date createDate;
    @JsonIgnore @JSONField(serialize = false)private String modifyBy;
    @JsonIgnore @JSONField(serialize = false)private Date modifyDate;
    @JsonIgnore @JSONField(serialize = false)private String version;
}
