package co.edu.udistrital.Comunicacion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Indica que esta clase es una configuración de Spring
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Método para configurar CORS (Cross-Origin Resource Sharing)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configura las reglas de CORS
        registry.addMapping("/**") // Autoriza todas las URL del servidor
                .allowedOrigins("*") // Permite solicitudes desde cualquier origen
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite los métodos HTTP especificados
                .allowedHeaders("*"); // Permite todos los encabezados
    }
}

