package vn.alpaca.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.alpaca.demo.Domain.ProductEntity;
import vn.alpaca.demo.Services.ProductService;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final ProductService productService;

    @Autowired
    public StoreController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addProduct(){
        return "add";
    }

    @PostMapping("/add")
    public String add(@RequestBody ProductEntity product){
        productService.createProduct(product);
        return "Success";
    }
}
