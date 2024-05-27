package co.edu.udistrital.Personas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "postulantes")
@Data
@EqualsAndHashCode(callSuper = true)
public class Postulante extends Persona{
    private String interesPrincipal;
    private boolean disponibilidad;
}
