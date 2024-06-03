package co.edu.udistrital.Citas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String buscador;
    private String cedulaBuscador;
    private String calificacionBuscador;
    private String postulante;
    private String cedulaPostulante;
    private String calificacionPostulante;
    private String fecha;
    private String calificacion;


    public Cita(String cedulaBuscador, String nombreBuscador, String cedulaPostulante, String nombrePostulante) {
        this.cedulaBuscador = cedulaBuscador;
        this.buscador = nombreBuscador;
        this.cedulaPostulante = cedulaPostulante;
        this.postulante = nombrePostulante;
    }
}
