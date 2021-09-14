package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Product;
import uz.pdp.appwarehouse.entity.User;
import uz.pdp.appwarehouse.payload.ProductDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.payload.UserDto;
import uz.pdp.appwarehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto){
        Result result = productService.addProduct(productDto);
        return result;
    }

    //Delete
    @DeleteMapping(value = "/byProductId/{productId}")
    public Result deleteProduct(@PathVariable Integer productId) {
        Result result = productService.deleteProduct(productId);
        return result;
    }

    //Update
    @PutMapping (value = "/byProductId/{productId}")
    public Result editProduct(@PathVariable Integer productId, @RequestBody ProductDto productDto) {
        Result result = productService.editProduct(productId, productDto);
        return result;
    }

    @GetMapping
    public List<Product> getProducts() {
        List<Product> ProductList = productService.getProducts();
        return ProductList;
    }
}
