package com.kht.ecommerce.ecommerce_application.mapper;

import com.kht.ecommerce.ecommerce_application.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // DB 에서 데이터를 가져올 때만 DTO 사용
    // 사용자 조회
    List<User> getAllUsers();

    //사용자 저장 insert 가 1개일 경우나 갯수 상관이 없다면 void 사용,
    //갯수가 중요하다면 int 로 몇 개 넣었는지 반환
    void insertUser(User user);

    //이메일 존재 유무 확인
    //int GET = 몇 개 찾았는지
    int existByEmail(String email);

    //유저 상세보기
    User userDetail(int userId);

    //유저 정보 수정(업데이트)
    //수정을 1가지만 진행할 경우 void 로 작성할 수 있음.
    //하지만, 갯수가 여러개일 경우 int 사용하는 것이 좋음
    void updateUser(User user);




}
