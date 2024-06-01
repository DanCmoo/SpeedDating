package co.edu.udistrital.Comunicacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

public class ComunicacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComunicacionApplication.class, args);
	}

}
