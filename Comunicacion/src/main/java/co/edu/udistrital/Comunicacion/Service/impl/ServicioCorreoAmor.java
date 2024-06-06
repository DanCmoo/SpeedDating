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

// Indica que esta clase es un servicio de Spring
@Service
public class ServicioCorreoAmor implements iServicioCorreo {

    // Inyección de dependencias
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    // Constructor para inyectar JavaMailSender y TemplateEngine
    public ServicioCorreoAmor(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    /**
     * Envía un correo electrónico con el resultado de una cita de amor.
     *
     * @param correo El modelo de correo que contiene la información necesaria para enviar el correo.
     * @throws MessagingException Si ocurre un error durante el proceso de envío del correo.
     */
    @Override
    public void enviarCorreo(ModeloCorreo correo) throws MessagingException {
        // Crear el primer mensaje de correo electrónico
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");
        helper.setTo(correo.getCorreoBuscador()); // Establece el destinatario
        helper.setSubject("RESULTADO CITA"); // Establece el asunto del correo

        // Configurar el contexto para la plantilla de correo
        Context context = new Context();
        context.setVariable("nombreInteresado", correo.getNombrePostulante());
        context.setVariable("nombreDestinatario", correo.getNombreBuscador());
        context.setVariable("correoInteresado", correo.getCorreoPostulante());
        context.setVariable("telefonoInteresado", correo.getTelefonoPostulante());

        // Procesar la plantilla de correo y generar el contenido HTML
        String contenidoHTML = templateEngine.process("CorreoAmor", context);
        helper.setText(contenidoHTML, true); // Establece el contenido del correo
        javaMailSender.send(mensaje); // Envía el correo

        // Crear el segundo mensaje de correo electrónico
        MimeMessage mensaje2 = javaMailSender.createMimeMessage();
        MimeMessageHelper helper2 = new MimeMessageHelper(mensaje2, true, "UTF-8");
        helper2.setTo(correo.getCorreoPostulante()); // Establece el destinatario
        helper2.setSubject("Resultado cita"); // Establece el asunto del correo

        // Configurar el contexto para la plantilla de correo
        Context context2 = new Context();
        context2.setVariable("nombreInteresado", correo.getNombreBuscador());
        context2.setVariable("nombreDestinatario", correo.getNombrePostulante());
        context2.setVariable("correoInteresado", correo.getCorreoBuscador());
        context2.setVariable("telefonoInteresado", correo.getTelefonoBuscador());

        // Procesar la plantilla de correo y generar el contenido HTML
        String contenidoHTML2 = templateEngine.process("CorreoAmor", context2);
        helper2.setText(contenidoHTML2, true); // Establece el contenido del correo
        javaMailSender.send(mensaje2); // Envía el correo
    }

}

