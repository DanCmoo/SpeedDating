package co.edu.udistrital.Comunicacion.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.tools.JavaFileManager;
import java.util.Properties;

// Indica que esta clase es una configuración de Spring
@Configuration
// Especifica el archivo de propiedades que se utilizará
@PropertySource("classpath:correo.properties")
public class CorreoConfig {

    // Inyecta el valor de la propiedad 'correo.usuario' desde el archivo de propiedades
    @Value("${correo.usuario}")
    private String correo;

    // Inyecta el valor de la propiedad 'correo.contraseña' desde el archivo de propiedades
    @Value("${correo.contraseña}")
    private String contraseña;

    // Método privado para configurar las propiedades del correo
    /**
     * Obtiene las propiedades de configuración necesarias para enviar correos electrónicos.
     *
     * @return Un objeto Properties con las propiedades configuradas para SMTP.
     */
    private Properties getCorreoProperties() {
        Properties properties = new Properties();
        // Habilita la autenticación SMTP
        properties.put("mail.smtp.auth", "true");
        // Habilita el uso de STARTTLS
        properties.put("mail.smtp.starttls.enable", "true");
        // Configura el host del servidor SMTP
        properties.put("mail.smtp.host", "smtp.gmail.com");
        // Configura el puerto del servidor SMTP
        properties.put("mail.smtp.port", "587");
        return properties;
    }

    /**
     * Define un bean para enviar correos electrónicos.
     *
     * @return Una instancia de JavaMailSender configurada con las propiedades y credenciales necesarias.
     */
    @Bean
    public JavaMailSender javaMailSender() {
        // Crea una instancia de JavaMailSenderImpl
        JavaMailSenderImpl correoEnviar = new JavaMailSenderImpl();
        // Establece las propiedades de correo electrónico
        correoEnviar.setJavaMailProperties(getCorreoProperties());
        // Establece el nombre de usuario del correo electrónico
        correoEnviar.setUsername(correo);
        // Establece la contraseña del correo electrónico
        correoEnviar.setPassword(contraseña);
        return correoEnviar;
    }

    /**
     * Define un bean para cargar recursos.
     *
     * @return Una instancia de DefaultResourceLoader.
     */
    @Bean
    public ResourceLoader resourceLoader() {
        return new DefaultResourceLoader();
    }

}

