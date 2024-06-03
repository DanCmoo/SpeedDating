package co.edu.udistrital.Citas.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class Postulante extends Persona{
    private String interesPrincipal;
    private boolean disponibilidad;
}
