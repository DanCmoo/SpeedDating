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

// Indica que esta clase es un servicio de Spring
@Service
public class CitaServiceimpl implements iCitaService {

    // Inyección de la dependencia RestTemplate
    @Autowired
    private RestTemplate restTemplate;

    /**
     * Genera un PDF con un listado de citas y sus calificaciones.
     *
     * @return Un ByteArrayInputStream que contiene el contenido del PDF generado.
     */
    @Override
    public ByteArrayInputStream generatePdf() {
        // Llama al endpoint para obtener la lista de citas
        Cita[] citaList = restTemplate.getForObject("http://localhost:8082/citas/listar", Cita[].class);

        // ByteArrayOutputStream para escribir el contenido del PDF en memoria
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // Crear instancias de PdfWriter, PdfDocument y Document para generar el PDF
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Agregar un párrafo inicial al documento
            document.add(new Paragraph("Listado de Citas Amistad"));

            // Crear tablas para las diferentes calificaciones
            Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
            Table table2 = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
            Table table3 = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();

            // Agregar encabezados a las tablas
            table.addHeaderCell("Nombre Buscador");
            table.addHeaderCell("Puntuacion Buscador");
            table.addHeaderCell("Nombre Postulante");
            table.addHeaderCell("Puntuación postulante");

            table2.addHeaderCell("Nombre Buscador");
            table2.addHeaderCell("Puntuacion Buscador");
            table2.addHeaderCell("Nombre Postulante");
            table2.addHeaderCell("Puntuación postulante");

            table3.addHeaderCell("Nombre Buscador");
            table3.addHeaderCell("Puntuacion Buscador");
            table3.addHeaderCell("Nombre Postulante");
            table3.addHeaderCell("Puntuación postulante");

            // Iterar sobre la lista de citas y agregarlas a las tablas correspondientes
            if(!(citaList == null)){
                for (Cita cita : citaList) {
                    if (cita.getCalificacion().equals("Amistad")) {
                        table.addCell(cita.getBuscador());
                        table.addCell(cita.getCalificacionBuscador());
                        table.addCell(cita.getPostulante());
                        table.addCell(cita.getCalificacionPostulante());
                    } else if (cita.getCalificacion().equals("Más que amistad")) {
                        table2.addCell(cita.getBuscador());
                        table2.addCell(cita.getCalificacionBuscador());
                        table2.addCell(cita.getPostulante());
                        table2.addCell(cita.getCalificacionPostulante());
                    } else if(cita.getCalificacion().equals("No conexión")){
                        table3.addCell(cita.getBuscador());
                        table3.addCell(cita.getCalificacionBuscador());
                        table3.addCell(cita.getPostulante());
                        table3.addCell(cita.getCalificacionPostulante());
                    }
                }
            }


            // Agregar las tablas al documento con sus respectivos títulos
            document.add(table);
            document.add(new Paragraph("Listado de Citas Más que amistad"));
            document.add(table2);
            document.add(new Paragraph("Listado de Citas No conexión"));
            document.add(table3);

            // Cerrar el documento
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Devolver el contenido del PDF como un ByteArrayInputStream
        return new ByteArrayInputStream(out.toByteArray());
    }
}

