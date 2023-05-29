package com.example.product.service;

import com.example.product.dto.ProductRequestDTO;
import com.example.product.dto.ProductResponceDTO;
import com.example.product.entity.ProductEntity;
import com.example.product.mapper.ProductMapper;
import com.example.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private  final ProductMapper mapper;

    public List<ProductResponceDTO> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return products.stream().
                map(n->mapper.productResponcemapper(n)).collect(Collectors.toList());
    }

    public void createProduct(ProductRequestDTO productDTO) {
        ProductEntity existingProduct = productRepository.findByName(productDTO.getName());

        if (productRepository.existsByName(productDTO.getName())) {
            throw new ProductAlreadyExistsException("Product with name '" + productDTO.getName() + "' already exists");

        } else {
            ProductEntity product = mapper.prodctrequestmapper(productDTO);
            productRepository.save(product);
        }

    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public void reStock(Long id,int change){
        ProductEntity product = productRepository.findProductEntityById(id);
         product.setQuantity(change);
        productRepository.save(product);
    }

    public ProductResponceDTO getProduct(Long id) {
     return  mapper.productResponcemapper(productRepository.findProductEntityById(id)) ;
    }

    public void updateProduct(Long id,ProductRequestDTO productDTO) {
        ProductEntity product = productRepository.findProductEntityById(id);
        product.setCost(productDTO.getCost());
        product.setId(product.getId());
        product.setName(product.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(product.getQuantity());
        productRepository.save(product);

    }



}
