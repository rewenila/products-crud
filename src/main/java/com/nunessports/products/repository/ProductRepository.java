package com.nunessports.products.repository;

import com.nunessports.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Product findByCode(String code);
}
