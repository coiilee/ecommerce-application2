package com.kht.ecommerce.ecommerce_application.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth/kakao")
public class KakaoApiController {

    //${변수이름} 은 application.properties 나 config.properties 에 작성한 변수 이름 가져오기
    //변수이름에 해당하는 값을 불러오기

    @Value("${kakao.client-id}")
    private String kakaoClientId;

    /* config.properties에서 kakao.redirect-uri를 아래처럼 직접적으로 가져올 수 있음.
    private String kakao.redirect-uri=http://localhost:8080/oauth/kakao/callback

    하지만, java-spring 자체에서 보안을 가장 중요하게 생각하기 때문에
    아이디, 비밀번호와 같은 중요한 정보는 properties 파일로 나누어서
    @Value 값으로 호출해서 사용할 수 있도록 분류해주는 것이 바람직함.

     */
    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.client-secret}")
    private String kakaoClientSecret;
}
