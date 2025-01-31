package com.kht.ecommerce.ecommerce_application.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//카카오톡 로그인 버튼 회원가입 html이 보이는 view Controller
@Controller
public class KakaoViewController {

    // 1. kakaoLogin.html 연결하는 view 생성
    @GetMapping("/kakaoLogin")
    public String kakaoLogin() {
        return "kakaoLogin";
    }

    //2. /kakaoSignUp kakaoSignUp.html view 생성
    @GetMapping("/kakaoSignUp")
    public String kakaoSignUp() {
        return "kakaoSignUp";
    }
}
