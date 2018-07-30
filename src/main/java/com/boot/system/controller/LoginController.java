package com.boot.system.controller;

import com.boot.common.annotation.DataAPI;
import com.boot.common.utils.ShiroUtils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @GetMapping({"/","","login"})
    public  String login(){
        return "login";
    }

    @PostMapping("/doLogin")
    @ResponseBody
    @DataAPI
    public Object doLogin(@RequestParam String username, @RequestParam String password){
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		return "ok";
	}

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/logout")
    public String logout(){
        ShiroUtils.logout();
        return "login";
    }
}
