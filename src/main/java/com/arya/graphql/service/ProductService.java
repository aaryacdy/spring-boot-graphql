package com.arya.graphql.service;

import com.arya.graphql.dto.ProductRequest;
import com.arya.graphql.dto.ProductResponse;
import com.arya.graphql.util.Response;

import java.util.List;

public interface ProductService {

    Response saveProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProduct();

    boolean isNameUnique(String name);

}
