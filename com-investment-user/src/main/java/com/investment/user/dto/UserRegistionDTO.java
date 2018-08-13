package com.investment.user.dto;

import lombok.Data;

@Data
public class UserRegistionDTO {
    private String mobile;
    private String password;
    private String smsCode;
}
