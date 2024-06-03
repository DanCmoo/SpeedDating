package co.edu.udistrital.pdf.controller;

import co.edu.udistrital.pdf.services.iCitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class PdfController {
    @Autowired
    private iCitaService iCitaService;
    @GetMapping("/pdf")
    public ResponseEntity <byte[]> generarPdf(){
        ByteArrayInputStream pdf= iCitaService.generatePdf();
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Disposition","inLine;Filename=Citas.pdf");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(pdf.readAllBytes());
    }
}
