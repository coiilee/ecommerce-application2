package com.kht.ecommerce.ecommerce_application.service;

import com.kht.ecommerce.ecommerce_application.dto.VerificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class VerificationService {
    @Autowired
    private JavaMailSender mailSender;

    private Map<String,String> verificationCodes = new HashMap<String,String>();

    //랜덤 6자리 숫자 생성
    public String randomCode(){
        Random random = new Random();
        int randomNum = 100000 + random.nextInt(900000);  //900000이하의 랜덤 숫자에 100000더한 100000~999999까지
        return String.valueOf(randomNum); //숫자값을 글자 형식으로 변환해서 전달. 왜? mail의 기본 타입은 String.
    }

    //보낼 메일의 제목, 내용이 들어있는 메서드
    public void sendEmail(String email, String code){
        SimpleMailMessage message = new SimpleMailMessage(); //메일 보낼때 간단한 양식 저장 코드
        message.setTo(email); //사용자가 작성한 이메일로 메세지 보낼 것
        message.setSubject("인증코드 전송"); //이메일 제목
        message.setText("인증코드 : "+code);

        mailSender.send(message);
        System.out.println("이메일을 성공적으로 보냈습니다." + email);
    }

    //보낸 이메일, 인증코드 저장하는 메서드
    public void saveEmailCode(String email, String code){
        verificationCodes.put(email.toLowerCase(),code); //보낸 이메일과 내용 저장, 이메일은 소문자로 저장
    }

    //이메일과 인증코드 일치 여부 확인
    public boolean verifyCodeWithVO(VerificationRequest request){
        String email = request.getEmail().toLowerCase(); //이메일 주소 가져오기
        String inputCode = request.getCode(); //인증코드 가져오기
        String saveCode = verificationCodes.get(email); //verificationcodes 라는 맵에서 키로 주어진 이메일 주소에 대응하는 인증코드 가져오기(map으로 썼으니 해당하는 value값을 가져오는것)
        return inputCode.equals(saveCode); //일치하는지 여부 true false로 확인하기
    }
}
