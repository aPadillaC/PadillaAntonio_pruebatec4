package com.hackaboss.agenciaTurismo;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgenciaTurismoApplication {

	public static void main(String[] args) {

		SpringApplication.run(AgenciaTurismoApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
				.title("API Agencia de Turismo")
				.version("0.0.1")
				.description("Una API para la gestión de hoteles, habitaciones, vuelos y reservas que permite a " +
						"los usuarios buscar y reservar hoteles y vuelos, así como administrar sus reservas. Incluye " +
						"funciones para la gestión de hoteles y habitaciones, la consulta de vuelos, y operaciones de " +
						"reserva y cancelación. Ofrece autenticación de los empleados en la actualización de las " +
						"entidades para garantizar la seguridad y el acceso adecuado a los recursos."));
	}
}
