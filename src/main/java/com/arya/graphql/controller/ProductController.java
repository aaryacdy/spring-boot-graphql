package com.arya.graphql.controller;

import com.arya.graphql.dto.ProductRequest;
import com.arya.graphql.dto.ProductResponse;
import com.arya.graphql.service.ProductService;
import com.arya.graphql.util.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Response> saveProduct(@RequestBody ProductRequest productRequest) {
        var response = productService.saveProduct(productRequest);
        if (response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllUsers() {
        return productService.getAllProduct();
    }

}
