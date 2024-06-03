package co.edu.udistrital.Comunicacion.Service;

import co.edu.udistrital.Comunicacion.models.ModeloCorreo;
import jakarta.mail.MessagingException;


public interface iServicioCorreo {
    public void enviarCorreo(ModeloCorreo correo) throws MessagingException;
}
