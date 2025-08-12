package com.chethan.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chethan.demo.Repository.ProductRepository;
import com.chethan.demo.model.Product;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    public Product getproductById(Long id){
        return repo.findById(id).orElse(new Product());
    }

    public Product addProduct(Product product) {
        return repo.save(product);
    }

    public void updateproduct(Product id) {
        repo.save(id);
    }

    public void deleteproduct(Long id) {
        repo.deleteById(id);
    }


    public List<Product> searchProducts(String name, String description, Double price, Integer quantity) {
        return repo.searchProducts(name, description, price, quantity);
    }


    
}
