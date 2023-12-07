package com.example.retrofitapirest.dto;
//Importa las anotaciones de Lombok que simplifican la creación de constructores, métodos getter/setter y otros métodos comunes
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//Anotaciones de Lombok para generar automáticamente un constructor sin argumentos, un constructor con todos los argumentos,
//métodos getter/setter y otros métodos comunes.
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO {
	// Define los campos del DTO
	 private String nombre;
	 private String correo;
	 private String clave;
}



