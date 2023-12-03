// Define el paquete y el nombre del controlador
package com.example.retrofitapirest.controller;

// Importa las clases necesarias
import com.example.retrofitapirest.dto.ProductDTO;
import com.example.retrofitapirest.model.Product;
import com.example.retrofitapirest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Anota la clase como un controlador de Spring MVC
@RestController
// Habilita el intercambio de recursos entre dominios (CORS)
@CrossOrigin
// Define la raíz de la URL para todas las solicitudes manejadas por este controlador
@RequestMapping("/product")
public class ProductController {

    // Inyecta una instancia de ProductRepository utilizando la anotación @Autowired
    @Autowired
    private ProductRepository productRepository;

    // Maneja las solicitudes HTTP GET para obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    // Maneja las solicitudes HTTP GET para obtener un producto por su ID
    @GetMapping("{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id") int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Maneja las solicitudes HTTP POST para crear un nuevo producto
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    // Maneja las solicitudes HTTP PUT para actualizar un producto existente por su ID
    @PutMapping("{id}")
    public ResponseEntity<Product> update(@PathVariable("id") int id, @RequestBody ProductDTO dto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            Product updatedProduct = productRepository.save(product);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Maneja las solicitudes HTTP DELETE para eliminar un producto por su ID
    @DeleteMapping("{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productRepository.delete(product);
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
