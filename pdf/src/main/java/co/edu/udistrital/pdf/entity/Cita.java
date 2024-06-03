package co.edu.udistrital.pdf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cita {
    private String buscador;
    private String cedulaBuscador;
    private String postulante;
    private String cedulaPostulante;
    private String calificacionBuscador;
    private String calificacionPostulante;
    private String calificacion;
    private String fecha;

}
