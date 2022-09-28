package com.rabinpant.respository;

import com.rabinpant.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByName(String name);

    List<Product> findByProductType(String productType);

    List<Product> findByPriceAndProductType(double price, String productType);

    //query by our own::
    //@Query(value = "SELECT * FROM PRODUCT_TBL WHERE PRICE=?1",nativeQuery = true)
    @Query("from Product p where p.price=?1")//position based param

   // @Query("from PRODUCT_TBL p where p.price=:price") //name parameter based index
    List<Product> getProductByPrice(double price);

    //    IN CLAUSE:
    List<Product> findByPriceIn(List<Double> prices);

    // Between Clause:
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    //Greater than
    List<Product> findByPriceGreaterThan(double price);
    //Less than
    List<Product> findByPriceLessThan(double price);

    //Like Operator operation in JPA
    List<Product> findByNameIgnoreCaseContaining(String name);
}
