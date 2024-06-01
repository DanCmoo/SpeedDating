package Controladores;

import Service.iServicioCorreo;
import Service.models.ModeloCorreo;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class ControlCorreo {
    @Autowired
    iServicioCorreo servicioCorreo;
    @PostMapping("/correo")
    private ResponseEntity<String> sendEmail(@RequestBody ModeloCorreo correo) throws MessagingException {
        servicioCorreo.enviarCorreo(correo);
        return new ResponseEntity<>("Correo enviado exitosamente", HttpStatus.OK);

    }
}
