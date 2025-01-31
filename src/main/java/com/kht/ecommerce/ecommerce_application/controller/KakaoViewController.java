package com.kht.ecommerce.ecommerce_application.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//카카오톡 로그인 버튼 회원가입 html이 보이는 view Controller
@Controller
public class KakaoViewController {

    // 1. kakaoLogin.html 연결하는 view 생성
    @GetMapping("/kakaoLogin")
    public String kakaoLogin() {
        return "kakaoLogin";
    }

    //2. /kakaoSignUp kakaoSignUp.html view
    //signup or kakaoSignup 상관없이 500 parameter-lang 오류
    //redirect 전달 받을 때 ? 키=값 전달
    //전달할 때 요청받은 requestParam 값이 존재하지 않으면 500 에러 발생함
    @GetMapping("/signup")
    public String kakaoSignUp(@RequestParam("nickname")String nickname,
                              @RequestParam("email") String email,
                              @RequestParam("profileImage") String profileImage, Model model){
        model.addAttribute("nickname", nickname);
        model.addAttribute("email", email);
        model.addAttribute("profileImage", profileImage);
        return "kakaoSignUp";
    }
}
