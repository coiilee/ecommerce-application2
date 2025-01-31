package com.kht.ecommerce.ecommerce_application.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

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
    @Value("${kakao.redirect-uri}") // = ${REDIRECT-URI}
    private String redirectUri;

    @Value("${kakao.client-secret}")
    private String kakaoClientSecret;

    @GetMapping("/login")
    public ResponseEntity<?> getKakaoLoginUrl() { //ResponseEntity<?>는 작성을 안해도 됨.
        // 현재 제대로 진행되고 있는지 상태 확인일 뿐.

        // 카카오톡 개발 문서에서 카카오로그인 > 예제 > 요청에 작성된 주소를 그대로 가져온 상태
        String url = "https://kauth.kakao.com/oauth/authorize?response_type=code" +
                "&client_id=" + kakaoClientId +
                "&redirect_uri=" + redirectUri;
        return ResponseEntity.ok(url);
    }

    //kakao.redirect-uri=http://localhost:8080/oauth/kakao/callback
    // 위에서 login 할건지 말건지 정해서 로그인 할거면 토큰을 주는것임
    @GetMapping("/callback")    //oauth/kakao/callback
    public String handleCallback(@RequestParam String code) {
        String tokenUrl = "https://kauth.kakao.com/oauth/token";

        RestTemplate restTemplate = new RestTemplate();

        // 카카오 개발자 문서에 헤더 요청,설명 제공했기 때문에 필수 작성한 것
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");//charset=utf-8 작성 안할경우 한글 깨짐

        // 카카오 개발자 문서 내 본문에 제공된 이름,타입,설명에 맞는 코드 작성한 것 (grant_type, client_id, redirect_uri, code 등)
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoClientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        if (kakaoClientSecret != null) {
            params.add("client_secret", kakaoClientSecret);
        }

        // 개발자 문서 내 응답 (HttpEntity로 주로 사용됨)
        HttpEntity<LinkedMultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        // 개발자 문서 내 응답 > 본문에 제공된 이름,타입,설명 필수 부분 작성한 것
        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);
        String accessToken = (String) response.getBody().get("access_token");

        String userInfoUrl = "https://kapi.kakao.com/v2/user/me";
        HttpHeaders userHeaders = new HttpHeaders();
        userHeaders.add("Authorization", "Bearer " + accessToken);

        HttpEntity<String> userRequest = new HttpEntity<>(userHeaders);
        ResponseEntity<Map> userResponse = restTemplate.postForEntity(userInfoUrl, userRequest, Map.class);

        // 여기 아래부터 프로젝트에 맞게 카카오에서 가져올 값 수정해서 사용
        Map userInfo = userResponse.getBody();
        System.out.println("==============Controller : userinfo=====================");
        System.out.println(userInfo);

        Map<String, Object> properties = (Map<String, Object>) userInfo.get("properties");

        Map<String, Object> kakaoAccount = (Map<String, Object>) userInfo.get("kakao_account");

        //추후 프로젝트에 맞게 카카오에서 가져올 값 설정
        //닉네임
        String nickname = (String) properties.get("nickname"); // nickname 이외의 것도 get으로 추가할수 있음 (gender, email 등등... )
        //이메일 ( 내 애플리케이션>제품 설정>카카오 로그인>동의항목 에서 이메일 필수 동의)
        String email = (String) kakaoAccount.get("email");
        //프로필 이미지
        String profileImage = (String) properties.get("profile_image");

        //한글 깨짐 방지
        String encodedNickname = URLEncoder.encode(nickname, StandardCharsets.UTF_8);
//        email의 경우 영어+숫자 형식이기 때문에 -> 변환할 필요 XX !! UTF_8은 입력값이 영어가 아닌 한글,특수문자일 때 사용
//        String encodedEmail = URLEncoder.encode(email, StandardCharsets.UTF_8);



        // 키-값 받아오기 위해 키-값 시작을 알리는 것은 '?' 기호를 사용함
        // 키-값 여러 값을 받아오고 전달할 경우는 '&' 기호로 키-값 다수 사용
        return "redirect:/signup?nickname=" + encodedNickname + "&email=" + email + "&profileImage=" + profileImage; //image에는 = 안붙여도되나
                                                                // 값 추가할 때는 '&'으로 연결.
                                                                // 주소에서 ? 는 항상 하나만 사용됨

    }

}

