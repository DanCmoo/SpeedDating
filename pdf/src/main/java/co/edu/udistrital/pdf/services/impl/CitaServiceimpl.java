package co.edu.udistrital.pdf.services.impl;

import co.edu.udistrital.pdf.entity.Cita;
import co.edu.udistrital.pdf.repository.CitaRepository;
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

import java.io.ByteArrayInputStream;
import java.util.List;

public class CitaServiceimpl implements iCitaService {
    @Autowired
    private CitaRepository citaRepository;

    @Override
    public ByteArrayInputStream generatePdf() {
        List<Cita> citaList = citaRepository.findAll();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            document.add(new Paragraph("Listado de Citas"));
            Table table = new Table(UnitValue.createPercentArray(4)).useAllAvailableWidth();
            table.addHeaderCell("Nombre Buscador");
            table.addHeaderCell("Puntuacion Buscador");
            table.addHeaderCell("Nombre Postulante");
            table.addHeaderCell("Puntuación postulante");
            for (Cita cita : citaList) {
                table.addCell(cita.getBuscador());
                table.addCell(cita.getCalificacionCitaBuscador());
                table.addCell(cita.getPostulante());
                table.addCell(cita.getCalificacionCitaPostulante());
            }
            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
