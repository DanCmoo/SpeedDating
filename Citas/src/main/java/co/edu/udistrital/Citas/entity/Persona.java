package co.edu.udistrital.Citas.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String estado;
    private String identidad;
    private String correo;
    private String telefono;
    private boolean pago;

}
