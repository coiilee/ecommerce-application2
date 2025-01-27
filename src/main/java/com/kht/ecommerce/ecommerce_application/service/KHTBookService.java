package com.kht.ecommerce.ecommerce_application.service;

import com.kht.ecommerce.ecommerce_application.dto.KHTBook;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface KHTBookService {

    List<KHTBook> getAllBooks();

    KHTBook bookDetail(int id);

    int bookUpdate(int id, String title, String author, String genre, MultipartFile imagePath);

    int deleteBook(int id);
}
