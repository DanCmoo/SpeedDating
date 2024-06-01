package Service;

import Service.models.ModeloCorreo;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;


public interface iServicioCorreo {
    public void enviarCorreo(ModeloCorreo correo) throws MessagingException;
}
