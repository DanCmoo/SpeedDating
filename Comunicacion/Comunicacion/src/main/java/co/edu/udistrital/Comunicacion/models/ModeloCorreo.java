package co.edu.udistrital.Comunicacion.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModeloCorreo {
    private String nombreBuscador;
    private String correoBuscador;
    private String nombrePostulante;
    private String correoPostulante;
    private String cita;
    private String telefonoBuscador;
    private String telefonoPostulante;
    private String mensaje;


}
