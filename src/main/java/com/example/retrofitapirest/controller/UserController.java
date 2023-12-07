package com.example.retrofitapirest.controller;

import java.util.List;
import java.util.Optional;

import com.example.retrofitapirest.dto.AuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.retrofitapirest.dto.ProductDTO;
import com.example.retrofitapirest.dto.UserDTO;
import com.example.retrofitapirest.model.Product;
import com.example.retrofitapirest.model.User;
import com.example.retrofitapirest.repository.UserRepository;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Maneja las solicitudes HTTP GET para obtener todos los productos
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> user = userRepository.findAll();
        return ResponseEntity.ok(user);
    }

    // Maneja las solicitudes HTTP GET para obtener un producto por su ID
    @GetMapping("{id}")
    public ResponseEntity<User> getOne(@PathVariable("id") int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Maneja las solicitudes HTTP POST para crear un nuevo producto
    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserDTO dto) {
        User user = new User();
        user.setNombre(dto.getNombre());
        user.setCorreo(dto.getCorreo());
        user.setClave(dto.getClave());
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    // Maneja las solicitudes HTTP PUT para actualizar un producto existente por su ID
    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable("id") int id, @RequestBody UserDTO dto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setNombre(dto.getNombre());
            user.setCorreo(dto.getCorreo());
            user.setClave(dto.getClave());
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AuthDto dto) {
        List<User> users = userRepository.findByCorreo(dto.getEmail());
        if (!users.isEmpty()) {
            for (User user : users) {
                if (dto.getPassword().equals(user.getClave())) {
                    return ResponseEntity.ok(user);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    // Maneja las solicitudes HTTP DELETE para eliminar un producto por su ID
    @DeleteMapping("{id}")
    public ResponseEntity<User> delete(@PathVariable("id") int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepository.delete(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
