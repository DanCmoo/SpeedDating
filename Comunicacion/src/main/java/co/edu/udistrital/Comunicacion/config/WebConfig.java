package co.edu.udistrital.Comunicacion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Indica que esta clase es una configuración de Spring
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configura las reglas de CORS (Cross-Origin Resource Sharing) para la aplicación.
     *
     * @param registry el registro de CORS utilizado para agregar las configuraciones.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Autoriza todas las URL
                .allowedOrigins("*") // Permitir solicitudes desde cualquier origen
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowedHeaders("*"); // Encabezados permitidos
    }

}

