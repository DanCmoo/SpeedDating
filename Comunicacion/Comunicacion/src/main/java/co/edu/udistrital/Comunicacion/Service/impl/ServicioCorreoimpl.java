package co.edu.udistrital.Comunicacion.Service.impl;
import co.edu.udistrital.Comunicacion.Service.iServicioCorreo;
import co.edu.udistrital.Comunicacion.models.ModeloCorreo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class ServicioCorreoimpl implements iServicioCorreo {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;


    public ServicioCorreoimpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }
    @Override
    public void enviarCorreo(ModeloCorreo correo) throws MessagingException {

            MimeMessage mensaje=javaMailSender.createMimeMessage();
            MimeMessageHelper helper= new MimeMessageHelper(mensaje,true,"UTF-8");
            helper.setTo(correo.getCorreoBuscador());
            helper.setSubject("Resultado cita");
            Context context=new Context();
            context.setVariable("nombreInteresado",correo.getNombrePostulante());
            context.setVariable("nombreDestinatario", correo.getNombreBuscador());
            context.setVariable("correoInteresado", correo.getCorreoPostulante());
            context.setVariable("telefonoInteresado", correo.getTelefonoPostulante());
            String contenidoHTML=templateEngine.process("Correo",context);
            helper.setText(contenidoHTML,true);
            javaMailSender.send(mensaje);
            MimeMessage mensaje2=javaMailSender.createMimeMessage();
            MimeMessageHelper helper2= new MimeMessageHelper(mensaje2,true,"UTF-8");
            helper2.setTo(correo.getCorreoPostulante());
            helper2.setSubject("Resultado cita");
            Context context2=new Context();
            context2.setVariable("nombreInteresado",correo.getNombreBuscador());
            context2.setVariable("nombreDestinatario", correo.getNombrePostulante());
            context2.setVariable("correoInteresado", correo.getCorreoBuscador());
            context2.setVariable("telefonoInteresado", correo.getTelefonoBuscador());
            String contenidoHTML2=templateEngine.process("Correo",context2);
            helper2.setText(contenidoHTML2,true);
            javaMailSender.send(mensaje2);



    }
}
