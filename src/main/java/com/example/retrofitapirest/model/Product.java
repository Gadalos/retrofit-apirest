package com.example.retrofitapirest.model;

// Importa las anotaciones de JPA
import jakarta.persistence.*;

// Anota la clase como una entidad JPA y especifica el nombre de la tabla en la base de datos
@Entity
@Table(name = "product")
public class Product {
    // Anota el campo 'id' como la clave primaria y especifica la estrategia de generación de valor automático
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Define los campos 'name' y 'price'
    private String name;
    private int price;

    // Constructor vacío requerido por JPA
    public Product() {
        // Constructor vacío requerido por JPA
    }

    // Constructor con argumentos para facilitar la creación de instancias
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // Getter y Setter para 'id'
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter y Setter para 'name'
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter y Setter para 'price'
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
