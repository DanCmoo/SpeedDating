package co.edu.udistrital.pdf.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Cita {
    private long id;
    private String buscador;
    private String Postulante;
    private String calificacionCitaBuscador;
    private String calificacionCitaPostulante;

}
