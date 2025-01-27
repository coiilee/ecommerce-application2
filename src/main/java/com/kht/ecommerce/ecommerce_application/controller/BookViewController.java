package com.kht.ecommerce.ecommerce_application.controller;

import com.kht.ecommerce.ecommerce_application.dto.KHTBook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/*

url 주소값을 작성할 때
? 물음표가 들어가는 파라미터는 맨 마지막에 작성
    /   /   /   ?     올바른 예
    /   ?   /   /     잘못된 예

parameter 중간에 위치한 값을 찾지 못하는 경우 많음

!!!!!!!!!!!  ? 가 들어가는 주소값은  ?를  맨 마지막에 작성 !!!!!!!!!!!!

 */

@Controller
public class BookViewController {

    @GetMapping("/book")
//    @GetMapping(value = "/book" , params ={} )가 기본값
    public String book() {
        return "books";
    }

    /*
    Mapping = 특정 주소 값으로 기능이나 전달하고자 하는 행위를 감싸서 한번에 전달
    GET     = DB에서 가져온 값을 전달할 것인가
    POST    = 저장할 것인가
    PUT     = 수정할 것인가
    DELETE  = 삭제할 것인가

    특정 행위를 주고받고 전달하는 위치 = api = url 과 같은 주소값 이기 때문에 value라고 사용
    @Mapping (value = "url 명칭 / api 명칭" , 주소값 뒤나 중간에 사용할 파라미터가 존재하는가? 기본값 = 존재 X)
     */

    //html 특정 파일로 이동할 때는 @RequestParam 사용
    //@GetMapping("/book/detail") 1. /books/detail?id=아이디값
    @GetMapping(value="/book", params = "id")
    public String detail(@RequestParam("id") int id) {
        return "book-detail";
    }

    // /books/update?id= 설정 BEST ! url 주소에서 ?는 제일 뒤에 쓰는게 맞음.
    @GetMapping("/book/update")
    public String edit(@RequestParam("id") int id) {
//        model.addAttribute("bookId", id);
        return "book-edit";
    }
}
