package com.rabinpant.Controller;

import com.rabinpant.Entity.Product;
import com.rabinpant.respository.ProductRepository;
import com.rabinpant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product saveProduct(@RequestBody Product product){

        return productService.saveProduct(product);

    }

    @GetMapping
    public List<Product> getProduct(){
        return productService.getProduct();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }
    @GetMapping("name/{name}")
    public Product getProductByName(@PathVariable String name){
        return productService.getProductByName(name);
    }
    @GetMapping("/type/{productType}")
    public List<Product> getProductByTypes(@PathVariable String productType){
        return productService.getProductByTypes(productType);
    }
    @GetMapping("/price/{price}/type/{productType}")
    public List<Product> getProductWithPriceAndType(@PathVariable double price,@PathVariable String productType){
        return productService.getProductWithPriceAndType(price,productType);
    }
    @GetMapping("/search/{price}")
    public List<Product> getProductByPrice(@PathVariable double price){
        return productService.getProductByPrice(price);
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id,@RequestBody Product product){

        /*get the product from the DB
         * update with new value getting from the request
         * */
        Product existingProduct = productService.getProductById(id).get();
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setProductType(product.getProductType());

        return productService.saveProduct(existingProduct);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(int id){

        productService.deleteProduct(id);
    }


}
