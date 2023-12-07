package com.example.retrofitapirest.repository;

import com.example.retrofitapirest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    List<User> findByCorreo(String correo);

}
