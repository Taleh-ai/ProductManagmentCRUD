package com.example.product.service;

import com.example.product.dto.ProductRequestDTO;
import com.example.product.dto.ProductResponceDTO;
import com.example.product.entity.ProductEntity;
import com.example.product.mapper.ProductMapper;
import com.example.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private  final ProductMapper mapper;

    public List<ProductResponceDTO> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return products.stream().
                map(n->mapper.productResponseMapper(n)).collect(Collectors.toList());


    }

    public void createProduct(ProductRequestDTO productDTO) {
        ProductEntity existingProduct = productRepository.findByName(productDTO.getName());

        if (productRepository.existsByName(productDTO.getName())) {
            throw new ProductAlreadyExistsException("Product with name '" + productDTO.getName() + "' already exists");

        } else {
            ProductEntity product = mapper.productRequestMapper(productDTO);
            log.info("Adding product with ProductRequestDTO dto :{}",productDTO.toString());
            productRepository.save(product);
            log.info(product.toString());
        }



    }
    public void deleteProduct(Long id) {

        ProductEntity product = productRepository.findProductEntityById(id);
        log.info("Deleting product service started with ProductEntity entity: {} ",product.toString());
        productRepository.deleteById(id);
        log.info("Deleting product service started with ProductEntity entity: {} ",
                mapper.productResponseMapper(product).toString());
    }
    public void reStock(Long id,int change){
        ProductEntity product = productRepository.findProductEntityById(id);
        log.info("Update product service started with id: {} and ProductEntity product :{}",id,
                product);
        log.info("Update product service started with id: {} and ProductEntity product :{}",id,
                mapper.productResponseMapper(product).toString());
         product.setQuantity(change);
        log.info("After updating entity with id: {} to this :{}",id,
                product);
        log.info("After updating entity with id: {} to this :{}",id,
                mapper.productResponseMapper(product).toString());
        productRepository.save(product);
    }

    public ProductResponceDTO getProduct(Long id) {
        ProductEntity entityById = productRepository.findProductEntityById(id);
        System.out.println(entityById);
        return  mapper.productResponseMapper(entityById) ;
    }

    public void updateProduct(Long id,ProductRequestDTO productDTO) {
        ProductEntity product = productRepository.findProductEntityById(id);
        product.setCost(productDTO.getCost());
        product.setId(product.getId());
        product.setName(product.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(product.getQuantity());
        ProductEntity save = productRepository.save(product);


    }
}
