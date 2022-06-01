package com.ward.photogram.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //IoC?? //파일을 리턴하는 컨트롤러
public class AuthController {


    @GetMapping("/auth/signin")
    public String signinForm() {

        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {

        return "auth/signup";
    }
    //회원가입버튼 -> /auth/signUp -> auth/signin
    @PostMapping("/auth/signup")
    public String signup() { //회원가입이 성공하면 로그인 페이지로 가면되니까
        return "auth/signin";
    }

}
