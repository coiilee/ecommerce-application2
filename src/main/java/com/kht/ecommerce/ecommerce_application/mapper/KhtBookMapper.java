package com.kht.ecommerce.ecommerce_application.mapper;

import com.kht.ecommerce.ecommerce_application.dto.KHTBook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface KhtBookMapper {

    List<KHTBook> getAllBooks();

    //특정 정보 조회할때는 맨앞에 파일명 작성
    KHTBook bookDetail(int bookId);

    // void, int 사용 가능
    // int  -> 수정한 행의 수
    // void ->
//    void bookUpdate(KHTBook book);
    int bookUpdate(int id, String title, String author, String genre, String imagePath); //multipart String으로 바꿔서 넣어주기

    int deleteBook(int id);

}
