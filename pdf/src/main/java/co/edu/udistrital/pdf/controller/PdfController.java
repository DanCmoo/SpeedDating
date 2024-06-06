package co.edu.udistrital.pdf.controller;

import co.edu.udistrital.pdf.services.iCitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

// Indica que esta clase es un controlador REST de Spring
@RestController
public class PdfController {

    // Inyección de la dependencia iCitaService
    @Autowired
    private iCitaService iCitaService;

    // Mapeo del método HTTP GET a la URL /pdf
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generarPdf() {
        // Llama al servicio iCitaService para generar el PDF
        ByteArrayInputStream pdf = iCitaService.generatePdf();

        // Configura los encabezados HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Citas.pdf"); // Indica que el contenido debe mostrarse en línea y establece el nombre del archivo

        try {
            // Devuelve la respuesta con el PDF generado
            return ResponseEntity.ok()
                    .headers(headers) // Añade los encabezados configurados
                    .contentType(MediaType.APPLICATION_PDF) // Establece el tipo de contenido como PDF
                    .body(pdf.readAllBytes()); // Lee todos los bytes del PDF y los establece como cuerpo de la respuesta
        } catch (IOException e) {
            // Maneja posibles excepciones de lectura del PDF
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

