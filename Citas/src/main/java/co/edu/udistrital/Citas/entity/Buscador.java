package co.edu.udistrital.Citas.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class Buscador extends Persona{
    private String gustoContextura;
    private String gustoInteres;
    private float gustoEstatura;
    private String gustoIdentidad;
    private int gustoEdad;


}
