package co.edu.udistrital.Personas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "buscadores")
public class Buscador extends Persona{
    private String gustoContextura;
    private String gustoInteres;
    private float gustoEstatura;
    private String gustoIdentidad;
    private int gustoEdad;


}
