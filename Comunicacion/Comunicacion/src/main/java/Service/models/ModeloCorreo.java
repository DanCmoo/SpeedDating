package Service.models;


public class ModeloCorreo {
    private String nombreBuscador;
    private String correoBuscador;
    private String nombrePostulante;
    private String correoPostulante;
    private String cita;
    private String telefonoBuscador;
    private String telefonoPostulante;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    private String mensaje;
    public String getNombreBuscador() {
        return nombreBuscador;
    }

    public String getCita() {
        return cita;
    }

    public void setCita(String cita) {
        this.cita = cita;
    }

    public String getTelefonoBuscador() {
        return telefonoBuscador;
    }

    public void setTelefonoBuscador(String telefonoBuscador) {
        this.telefonoBuscador = telefonoBuscador;
    }

    public String getTelefonoPostulante() {
        return telefonoPostulante;
    }

    public void setTelefonoPostulante(String telefonoPostulante) {
        this.telefonoPostulante = telefonoPostulante;
    }

    public void setNombreBuscador(String nombreBuscador) {
        this.nombreBuscador = nombreBuscador;
    }

    public String getCorreoBuscador() {
        return correoBuscador;
    }

    public void setCorreoBuscador(String correoBuscador) {
        this.correoBuscador = correoBuscador;
    }

    public String getNombrePostulante() {
        return nombrePostulante;
    }

    public void setNombrePostulante(String nombrePostulante) {
        this.nombrePostulante = nombrePostulante;
    }

    public String getCorreoPostulante() {
        return correoPostulante;
    }

    public void setCorreoPostulante(String correoPostulante) {
        this.correoPostulante = correoPostulante;
    }
}
