package com.investment.user.controller;

import com.alibaba.fastjson.JSON;
import com.investment.user.dto.UserRegistionDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistionController {
    /**
     *
     * @param json
     * @
     */
    @PostMapping("/registion")
    public void registion(@RequestBody String json){
        UserRegistionDTO  userRegistionDTO = JSON.parseObject(json,UserRegistionDTO.class);
//        MetricsDTO metricsDTO = JSON.parseObject(json,MetricsDTO.class);

    }
}
