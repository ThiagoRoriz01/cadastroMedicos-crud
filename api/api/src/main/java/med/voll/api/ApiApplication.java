package med.voll.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/* As anotações no Spring Boot são usadas para fornecer metadados e configurações adicionais
ao código da aplicação. Elas desempenham um papel fundamental no framework Spring,
permitindo que você configure, defina comportamentos e crie estruturas de aplicativos de forma concisa
e intuitiva.
 */
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
		// essa classe "SpringApplication" executando esse método "run" faz o projeto ser inicializado
	}

}
