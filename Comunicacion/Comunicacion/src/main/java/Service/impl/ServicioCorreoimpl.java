package Service.impl;
import Service.iServicioCorreo;
import Service.models.ModeloCorreo;
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
        if (correo.getCita()!=""){
            MimeMessage mensaje=javaMailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mensaje,true,"UTF-8");
            helper.setTo(correo.getCorreoBuscador());
            helper.setSubject("Resultado Cita");
            correo.setMensaje("Hola"+correo.getNombrePostulante()+"Espero te encuentres muy bien el dia de hoy, es de nuestro agrado informarte que tienes una cita con"+correo.getNombreBuscador()+"el"+correo.getCita());
            Context context=new Context();
            context.setVariable("mensaje", correo.getMensaje());
            String contenidoHTML=templateEngine.process("Correo",context);
            helper.setText(contenidoHTML,true);
            javaMailSender.send(mensaje);
            MimeMessage mensaje2=javaMailSender.createMimeMessage();
            MimeMessageHelper helper2=new MimeMessageHelper(mensaje2,true,"UTF-8");
            helper.setTo(correo.getCorreoPostulante());
            helper.setSubject("Resultado cita");
            Context context2=new Context();
            correo.setMensaje("Hola"+correo.getNombreBuscador()+"Espero te encuentres muy bien el dia de hoy, es de nuestro agrado informarte que tienes una cita con"+correo.getNombrePostulante()+"el"+correo.getCita());
            context.setVariable("mensaje", correo.getMensaje());
            String contenidoHTML2=templateEngine.process("Correo",context2);
            helper.setText(contenidoHTML,true);
            javaMailSender.send(mensaje2);
        }else {
            MimeMessage mensaje=javaMailSender.createMimeMessage();
            MimeMessageHelper helper= new MimeMessageHelper(mensaje,true,"UTF-8");
            helper.setTo(correo.getCorreoBuscador());
            helper.setSubject("Resultado cita");
            Context context=new Context();
            correo.setMensaje("Hola"+correo.getNombrePostulante()+"Es de nuestro agrado informarte que"+correo.getNombreBuscador()+"Esta interesa@ en entablar una amistad contigo, sus datos de contacto son"+correo.getCorreoBuscador()+"\n"+correo.getTelefonoBuscador());
            context.setVariable("mensaje",correo.getMensaje());
            String contenidoHTML=templateEngine.process("Correo",context);
            helper.setText(contenidoHTML,true);
            javaMailSender.send(mensaje);
            MimeMessage mensaje2=javaMailSender.createMimeMessage();
            MimeMessageHelper helper2= new MimeMessageHelper(mensaje,true,"UTF-8");
            helper.setTo(correo.getCorreoBuscador());
            helper.setSubject("Resultado cita");
            correo.setMensaje("Hola"+correo.getNombreBuscador()+"Es de nuestro agrado informarte que"+correo.getNombrePostulante()+"Esta interesa@ en entablar una amistad contigo, sus datos de contacto son"+correo.getCorreoPostulante()+"\n"+correo.getTelefonoPostulante());
            Context context2=new Context();
            context.setVariable("mensaje",correo.getMensaje());
            String contenidoHTML2=templateEngine.process("Correo",context2);
            helper.setText(contenidoHTML,true);
            javaMailSender.send(mensaje2);


        }
    }
}
