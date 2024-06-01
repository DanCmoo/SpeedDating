package co.edu.udistrital.Comunicacion.Controladores;
import co.edu.udistrital.Comunicacion.Service.iServicioCorreo;
import co.edu.udistrital.Comunicacion.models.ModeloCorreo;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comunicacion")
public class ControlCorreo {
    @Autowired
    private iServicioCorreo servicioCorreo;
    @PostMapping("/correo")
    private ResponseEntity<String> sendEmail(@RequestBody ModeloCorreo correo){
        try{
            servicioCorreo.enviarCorreo(correo);
        }catch (MessagingException ex){

        }

        return ResponseEntity.ok("Correo enviado correctamente");

    }
}
