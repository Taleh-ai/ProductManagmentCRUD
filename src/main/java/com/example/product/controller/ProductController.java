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

    @GetMapping("/{id}")
    public ProductResponceDTO getProduct(@PathVariable Long id){
       return service.getProduct(id);
    }
    @GetMapping("/all")
    public List<ProductResponceDTO> getProducts(){
        return service.getAllProducts();
    }
    @PostMapping("/add")
    public void addProduct(@RequestBody ProductRequestDTO dto){
        service.createProduct(dto);
    }
    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable Long id){
        service.deleteProduct(id);
    }

    @PutMapping("/update/{id}")
    public void updateProduct(@PathVariable Long id ,@RequestBody ProductRequestDTO dto){
        service.updateProduct(id,dto);
    }
    @PatchMapping("/restock/{id}")
    public void restock(@PathVariable Long id,@RequestBody int stock){
        service.reStock( id,  stock);
    }
}
