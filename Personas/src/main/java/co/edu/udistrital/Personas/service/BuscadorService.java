package co.edu.udistrital.Personas.service;

import co.edu.udistrital.Personas.entity.Buscador;

public interface BuscadorService {
    void crearBuscador(Buscador buscador);
    Buscador consultarBuscador(String cedula);
    void actualizarBuscador(String cedula,Buscador buscador);
    void eliminarBuscador(String cedula);





}
