package com.investment.user.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserLoginController {

    /**
     *  <p>登录</p>
     *  仅适用于正常的注册后，使用密码登录
     * @param json
     */
    public void loginNormal(@RequestBody String json){

    }

    /**
     * <p>登录</p>
     * 仅适用于手机验证码快捷方式登录登
     * @param json
     */
    public void loginSmsCode(@RequestBody String json){

    }

    /**
     * <p>登录</p>
     *  仅适用于第三方授权登录
     * @param json
     */
    public void loginThirdAuthorized (@RequestBody String json){

    }

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++){
            int hashCodeV = UUID.randomUUID().toString().hashCode();
//            if (hashCodeV < 0) {//有可能是负数
//                hashCodeV = -hashCodeV;
//            }
            System.out.println(hashCodeV);
        }

    }
}
