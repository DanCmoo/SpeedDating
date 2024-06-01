package co.edu.udistrital.Personas.service;

import co.edu.udistrital.Personas.entity.Buscador;
import co.edu.udistrital.Personas.entity.Postulante;

public interface PostulanteService {
    void crearPostulante(Postulante postulante);
    Postulante consultarPostulante(String cedula);
    void actualizarPostulante(String cedula,Postulante postulante);
    void eliminarPostulante(String cedula);
}