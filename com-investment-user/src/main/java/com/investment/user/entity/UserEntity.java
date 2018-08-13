package com.investment.user.entity;

import lombok.Data;

@Data
public class UserEntity extends BaseEntity {
    private String id;
    private String userNo;
    private String mobile;
    private String password;
}
