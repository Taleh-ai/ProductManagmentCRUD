package com.example.product.controller;

import com.example.product.dto.ProductRequestDTO;
import com.example.product.dto.ProductResponceDTO;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {
    private final ProductService service;

    @GetMapping("/getproduct/{id}")
    public ProductResponceDTO getproduct(@PathVariable Long id){
       return service.getProduct(id);
    }
    @GetMapping("/getproduct")
    public List<ProductResponceDTO> getproducts(){
        return service.getAllProducts();
    }
    @PostMapping("/addproduct")
    public void addProduct(ProductRequestDTO dto){
        service.createProduct(dto);
    }
    @DeleteMapping("/removeproduct/{id}")
    public void remove(@PathVariable Long id){
        service.deleteProduct(id);
    }

    @PutMapping("/update/{id}")
    public void updateproduct(Long id ,ProductRequestDTO dto){
        service.updateProduct(id,dto);
    }
    @PatchMapping("/restock/{id}")
    public void restock(Long id,int stock){
        service.reStock(id,stock);
    }
}
