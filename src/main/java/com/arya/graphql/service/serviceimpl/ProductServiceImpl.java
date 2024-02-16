package com.arya.graphql.service.serviceimpl;

import com.arya.graphql.dto.ProductRequest;
import com.arya.graphql.dto.ProductResponse;
import com.arya.graphql.model.Product;
import com.arya.graphql.repository.ProductRepository;
import com.arya.graphql.service.ProductService;
import com.arya.graphql.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Response saveProduct(ProductRequest productRequest) {
        var name = productRequest.getName();
        if (isNameUnique(name)) {
            return Response.builder()
                    .message("Name already exists.")
                    .success(false)
                    .build();
        }

        try {
            var product = Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .image(productRequest.getImage())
                    .build();
            productRepository.save(product);
            return Response.builder()
                    .message("Product saved successfully.")
                    .success(true)
                    .build();
        } catch (DataIntegrityViolationException e) {
            // Handle data integrity violation (e.g., duplicate key) gracefully
            return Response.builder()
                    .message("Failed to save product. Data integrity violation.")
                    .success(false)
                    .build();
        } catch (Exception e) {
            // Handle other exceptions gracefully
            return Response.builder()
                    .message("Failed to save product.")
                    .success(false)
                    .build();
        }
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToUserResponse).toList();
    }

    public ProductResponse mapToUserResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .image(product.getImage())
                .build();
    }

    @Override
    public boolean isNameUnique(String email) {
        return productRepository.existsByName(email);
    }
}
