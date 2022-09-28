package com.rabinpant.Controller;

import com.rabinpant.Entity.Product;
import com.rabinpant.respository.ProductRepository;
import com.rabinpant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.JsonPath;
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

    //IN Clause:
    @PostMapping("/prices")
    public List<Product> getProductsByMultiplePriceValue(@RequestBody List<Double> prices){
        return productService.getProductsByMultiplePriceValue(prices);
    }
    @GetMapping("/min/{minPrice}/max/{maxPrice}")
    //Between Clause: findBy+ which Field + clause name(In, Between)
    public List<Product> getProductsWithinPriceRange(@PathVariable double minPrice,@PathVariable double maxPrice){

        return productService.getProductsWithinPriceRange(minPrice,maxPrice);
    }
    //Greater Price Clause
    @GetMapping("/greater/{price}")
    public List<Product> getProductsWithHigherPrice(@PathVariable double price){
        return productService.getProductsWithHigherPrice(price);
    }
    //Less price Clause
    @GetMapping("/less/{price}")
    public List<Product> getProductsWithLessPrice(@PathVariable double price){
        return productService.getProductsWithLessPrice(price);
    }
    //Like operator: % in JPA
    @GetMapping("/like/{name}")
    public List<Product> getProductsWithLike(@PathVariable String name){
        return productService.getProductsWithLike(name);
    }


    // sorting:
    @GetMapping("sorting/{fieldName}")
    public List<Product> getProductsWithSorting(@PathVariable String fieldName){
        return productService.getProductsWithSorting(fieldName);
    }
    //pagination:
    @GetMapping("/page/{offset}/{limit}")
    public Page<Product> getProductsWithPageResponse(@PathVariable int offset, @PathVariable int limit){

        return productService.getProductsWithPageResponse(offset,limit);
    }
    //sorting and pagination in the same method:
    @GetMapping("/pageWithSort/{fieldName}/{offset}/{limit}")
    public Page<Product> getProductWithSortingAndPagination(@PathVariable String fieldName, @PathVariable int offset, @PathVariable int limit){
        return productService.getProductWithSortingAndPagination(fieldName, offset, limit);
    }

}
