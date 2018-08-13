package com.investment.user.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
    Map<String,Object> selectUserById(Long id);
}
