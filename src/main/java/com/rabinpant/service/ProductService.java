package com.rabinpant.service;

import com.rabinpant.Entity.Product;
import com.rabinpant.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    //IN Clause:
    public List<Product> getProductsByMultiplePriceValue(List<Double> prices){
        return productRepository.findByPriceIn(prices);
    }

    //Between Clause: findBy+ which Field + clause name(In, Between)
    public List<Product> getProductsWithinPriceRange(double minPrice, double maxPrice){

        return productRepository.findByPriceBetween(minPrice,maxPrice);
    }
    //Greater Price Clause
    public List<Product> getProductsWithHigherPrice(double price){
        return productRepository.findByPriceGreaterThan(price);
    }
    //Less price Clause
    public List<Product> getProductsWithLessPrice(double price){
        return productRepository.findByPriceLessThan(price);
    }
    //Like operator: % in JPA
    public List<Product> getProductsWithLike(String name){
        return productRepository.findByNameIgnoreCaseContaining(name);
    }


    // sorting:
    public List<Product> getProductsWithSorting(String fieldName){
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, fieldName));
    }
    //pagination:
    public Page<Product> getProductsWithPageResponse(int offset, int limit){

        return productRepository.findAll(PageRequest.of(offset,limit));
    }
    //sorting and pagination in the same method:
    public Page<Product> getProductWithSortingAndPagination(String fieldName, int offset, int limit){
        return productRepository.findAll(PageRequest.of(offset,limit).withSort(Sort.by(fieldName)));
    }

}
