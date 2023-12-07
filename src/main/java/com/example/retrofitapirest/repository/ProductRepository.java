package com.example.retrofitapirest.repository;

import com.example.retrofitapirest.model.Product;
import com.example.retrofitapirest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Puedes agregar métodos personalizados si es necesario

}
