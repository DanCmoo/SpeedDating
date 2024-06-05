package co.edu.udistrital.pdf.services.impl;

import co.edu.udistrital.pdf.entity.Cita;
import co.edu.udistrital.pdf.services.iCitaService;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;

@Service
public class CitaServiceimpl implements iCitaService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ByteArrayInputStream generatePdf() {
        Cita[] citaList = restTemplate.getForObject("http://localhost:8082/citas/listar ",Cita[].class);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

            try {
                PdfWriter writer = new PdfWriter(out);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);
                document.add(new Paragraph("Listado de Citas Amistad"));
                Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
                document.add(new Paragraph("Listado de Citas Mas que amistad"));
                Table table2=new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
                table.addHeaderCell("Nombre Buscador");
                table.addHeaderCell("Puntuacion Buscador");
                table.addHeaderCell("Nombre Postulante");
                table.addHeaderCell("Puntuación postulante");
                table2.addHeaderCell("Nombre Buscador");
                table2.addHeaderCell("Puntuacion Buscador");
                table2.addHeaderCell("Nombre Postulante");
                table2.addHeaderCell("Puntuación postulante");
                for (Cita cita : citaList) {
                    if (cita.getCalificacion() == "amistad"){
                        table.addCell(cita.getBuscador());
                        table.addCell(cita.getCalificacionBuscador());
                        table.addCell(cita.getPostulante());
                        table.addCell(cita.getCalificacionPostulante());
                    }else{
                        table.addCell(cita.getBuscador());
                        table.addCell(cita.getCalificacionBuscador());
                        table.addCell(cita.getPostulante());
                        table.addCell(cita.getCalificacionPostulante());

                }}


                document.add(table);
                document.close();

            } catch (Exception e) {
                e.printStackTrace();

            }
            return new ByteArrayInputStream(out.toByteArray());
        }
}
