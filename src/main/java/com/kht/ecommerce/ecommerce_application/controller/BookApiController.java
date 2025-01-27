package com.kht.ecommerce.ecommerce_application.controller;

import com.kht.ecommerce.ecommerce_application.dto.KHTBook;
import com.kht.ecommerce.ecommerce_application.service.KHTBookService;
import com.kht.ecommerce.ecommerce_application.service.KHTBookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookApiController {

    @Autowired
    private KHTBookServiceImpl khtBookService;

    @GetMapping("/bookList")
    public List<KHTBook> getBooks() {

        return khtBookService.getAllBooks();
    }

    @GetMapping("/book/{id}")
    public KHTBook bookDetail(@PathVariable int id) {

        return khtBookService.bookDetail(id);
    }

//    @PutMapping("/book/{id}/update")
//    public void bookUpdate(@PathVariable("id") int id, @RequestBody KHTBook book) {
//        book.setId(id);
//        return khtBookService.bookUpdate(book);
//
//    }

    @PutMapping("/book/{id}/update")
    public int bookUpdate( @PathVariable int id,
                               @RequestParam("title") String title,
                               @RequestParam("author") String author,
                               @RequestParam("genre") String genre,
                               @RequestParam("imagePath") MultipartFile imagePath) {
        System.out.println("============Controlelr 출력 ===================");
        System.out.println("title : "+title);
        System.out.println("author : "+author);
        System.out.println("genre : "+genre);
        System.out.println("imagePath : "+ imagePath);
        return khtBookService.bookUpdate(id, title, author, genre, imagePath);

//        return khtBookService.bookDetail(id);

    }




}
