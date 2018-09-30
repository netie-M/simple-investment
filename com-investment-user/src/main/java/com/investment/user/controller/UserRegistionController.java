package com.investment.user.controller;

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

    }
}
