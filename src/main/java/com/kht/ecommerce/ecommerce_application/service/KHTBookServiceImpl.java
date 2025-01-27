package com.kht.ecommerce.ecommerce_application.service;

import com.kht.ecommerce.ecommerce_application.dto.KHTBook;
import com.kht.ecommerce.ecommerce_application.mapper.KhtBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;


@Service
public class KHTBookServiceImpl implements KHTBookService {

    //config.properties 에 작성한 경로 가져오기
    @Value("${upload-img}")
    private String uploadImg;

    @Autowired
    private KhtBookMapper khtBookMapper;

    @Override
    public List<KHTBook> getAllBooks() {
        return khtBookMapper.getAllBooks();
    }

    @Override
    public KHTBook bookDetail(int id) {
        return khtBookMapper.bookDetail(id);
    }

    @Override
    public int bookUpdate(int id, String title, String author, String genre, MultipartFile imagePath) {

        System.out.println("============= Service 출력 ===================");
        System.out.println("title : "+title);
        System.out.println("author : "+author);
        System.out.println("genre : "+genre);
        System.out.println("imagePath : "+ imagePath);

        try {
            String imgPath = imagePath.getOriginalFilename(); //이미지에서 가져온 파일이름
            System.out.println("OriginalFilename :" + imgPath);
            //이미지 저장 경로에 이미지 파일을 저장하고,
            File file = new File(uploadImg, imgPath);
            // 어떤 파일을 저장할 것이다 (어디에+어떤 이름으로) = transferTo.
            imagePath.transferTo(file);

            KHTBook khtBook = new KHTBook();
            khtBook.setId(id);
            khtBook.setTitle(title);
            khtBook.setAuthor(author);
            khtBook.setGenre(genre);
            //진짜로 저장된 이미지 장소를 숨기고 사용자들에게는 images 경로로 보여주게끔 설정
            khtBook.setImagePath("/images"+imgPath);

            return khtBookMapper.bookUpdate(id, title, author, genre, imgPath);

        } catch (IOException e) {
            //개발자가 컴퓨터 작업에 문제가 있을 때 문제를 확인하는 멘트
            System.out.println("파일을 컴퓨터에 저장할 수 없고, 데이터베이스에 수정할 수 없습니다." + e);
            return 0;
        }
    }

//
//
//        //이미지 이름만 get 가져와서 String 위치 + 이미지 이름만 DB에 저장하자 작성
//
//
//
//        //1. 저장 경로 설정
//        String baseDir = System.getProperty("user.home") + "/Desktop/user-images/";
//        //2. 폴더 존재하지 않으면 생성
//        File imgFolder = new File(baseDir);
//        if(!imgFolder.exists()){
//            imgFolder.mkdirs();
//        }
//        //3. 이미지 파일 이름 가져오기
//        String fileName = imagePath.getOriginalFilename();
//        //4. 저장할 이미지 경로, 이름 합쳐서 이미지 저장하기
//        File imageFile = new File(baseDir + fileName);
//
//
//        try {
//            imagePath.transferTo(imageFile);
//
//
//            System.out.println("파일 업로드를 성공적으로 완료했습니다.");
//        } catch (Exception e) {
//            System.out.println(e.getMessage()+"이미지 저장에 실패했습니다.");
//        }


    }






