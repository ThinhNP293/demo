package vn.alpaca.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.alpaca.demo.Domain.ProductEntity;
import vn.alpaca.demo.Repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> getAllProduct(){
        return productRepository.findAll();
    }

    public void createProduct(ProductEntity product){
        productRepository.save(product);
    }
}
