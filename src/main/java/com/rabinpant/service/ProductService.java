package com.rabinpant.service;

import com.rabinpant.Entity.Product;
import com.rabinpant.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product){

        return productRepository.save(product);

    }

    public List<Product> getProduct(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id){
        return productRepository.findById(id);
    }
    public Product getProductByName(String name){
        return productRepository.findByName(name);
    }

    public List<Product> getProductByTypes(String productType){
        return productRepository.findByProductType(productType);
    }
    public List<Product> getProductWithPriceAndType(double price, String productType){
        return productRepository.findByPriceAndProductType(price,productType);
    }

    public List<Product> getProductByPrice(double price){
        return productRepository.getProductByPrice(price);
    }

    public Product updateProduct(int id, Product product){

        /*get the product from the DB
        * update with new value getting from the request
        * */
        Product existingProduct = productRepository.findById(id).get();
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setProductType(product.getProductType());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(int id){

        productRepository.deleteById(id);
    }

}
