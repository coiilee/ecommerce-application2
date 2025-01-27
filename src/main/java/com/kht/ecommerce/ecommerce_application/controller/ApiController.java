package com.kht.ecommerce.ecommerce_application.controller;

import com.kht.ecommerce.ecommerce_application.dto.Cart;
import com.kht.ecommerce.ecommerce_application.dto.Product;
import com.kht.ecommerce.ecommerce_application.dto.User;
import com.kht.ecommerce.ecommerce_application.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController

public class ApiController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CartServiceImpl cartService;

    // 사용자 목록 API
    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    //유저 정보 상세보기
    @GetMapping("/api/user/{userId}")
    public User apiUserById(@PathVariable("userId") int userId){
        return userService.userDetail(userId);
    }

    //사용자 수정하기 API
    // /api/user/edit/{id}
    @PutMapping("/api/user/edit/{id}") //PathVariable = 특정 사용자 데이터를 주고받는 장소
                                          // @Request = 데이터를 통째로 전달받거나, 일부분만 전달받아서 사용하거나 전달
    public void updateUser(@PathVariable int id, @RequestBody User user){
        System.out.println(user.toString());
        user.setId(id);
        userService.updateUser(user);

    }


    // 상품 목록 API
    @GetMapping("/api/products")
    public List<Product> getProducts() {

        return productService.getAllProducts();

    }

    // 특정 사용자의 장바구니 API
    // http://localhost:8080/api/carts?userId=1
    @GetMapping("/api/carts")
    public List<Cart> getCart(@RequestParam("userId")int userId){
        return cartService.getCartByUserId(userId);
    }
    //이메일 중복 확인
    @GetMapping("/api/existEmail")
    public boolean existEmail(@RequestParam("email")String email){
        return userService.existByEmail(email);
    }


    //상품 정보 상세보기
    @GetMapping("/api/product/{productId}")
    public Product apiProductById(@PathVariable("productId") int productId) {
        return productService.productDetail(productId);
        //db에서 가져온 데이터를 front-end로 전달
    }
    /*
    HTTP Status 500 – Internal Server Error 서버에서 생각지 못한 문제 발생
    Expected one result (or null) to be returned by selectOne(), but found: 3
     */


    /**
     Param = 파라미터 = 매개변수
     * @RequestParam : 부분적으로 저장할 때 사용함
     * @RequestBody  : 전체적으로 저장할 때 사용함
     *
     */

    @PostMapping("/api/join")
    public void join(@RequestBody User user) {
        log.info("join user: {}", user);
        userService.insertUser(user);
    }

    @PostMapping("/api/products/insert")
    public void addProduct(@RequestBody Product product) {
        log.info("add product: {}", product);
        productService.addProduct(product);
    }


}