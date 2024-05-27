package co.edu.udistrital.Personas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Persona {
    @Id
    private String cedula;
    private String nombre;
    private String apellido;
    private int edad;
    private float estatura;
    private String profesion;
    private String contextura;
    private Estado estado;
    private String identidad;
    private String correo;
    private String telefono;
    private boolean pago;

}
