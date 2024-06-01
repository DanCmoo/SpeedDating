package Config;
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

@Configuration
@PropertySource("classpath:correo.properties")
public class CorreoConfig {

    @Value("${correo.usuario}")
    private String correo;

    @Value("${correo.contraseña}")
    private String contraseña;

    private Properties getCorreoProperties(){
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls"+".enable","true");
        properties.put("mail.smtp.host","smtp"+".gmail.com");
        properties.put("mail.smtp.port","587");
        return properties;

    }
    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl correoEnviar=new JavaMailSenderImpl();
        correoEnviar.setJavaMailProperties(getCorreoProperties());
        correoEnviar.setUsername(correo);
        correoEnviar.setPassword(contraseña);
        return correoEnviar;
    }
    @Bean
    public ResourceLoader resourceLoader(){
        return new DefaultResourceLoader();

    }
}
