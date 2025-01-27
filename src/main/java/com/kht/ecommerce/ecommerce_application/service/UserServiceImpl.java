package com.kht.ecommerce.ecommerce_application.service;

import com.kht.ecommerce.ecommerce_application.dto.User;
import com.kht.ecommerce.ecommerce_application.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

@Autowired //다른 곳에서 작성된 속성이나 객체 파일을 호출하기
UserMapper userMapper;

//SecurityConfig 내부에 @Bean 태그로 설정한 BCRypto 호출해서 비밀번호 암호화 사용
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public void insertUser(User user){
        //저장할 때 비밀번호만 암호화 처리해서 저장! - passwordEncoder 설정
        user.setPassword(passwordEncoder.encode(user.getPassword())); //비밀번호 암호화 처리돼서 저장됨
        userMapper.insertUser(user); //DB에서 가져온 값을 return해서 반환할 필요 X
        //DB에 저장을 하는 것이지 데이터베이스에서 꺼내오는 것이 아니기 때문에 return 아닌 void 사용

    }

    @Override
    public boolean existByEmail(String email){
        return userMapper.existByEmail(email)>0; //true일 때 return 의미
    }

    @Override
    public User userDetail(int userId) {
        return userMapper.userDetail(userId);
    }

    //사용자 정보 업데이트
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }


}
