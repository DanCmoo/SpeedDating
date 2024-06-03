package co.edu.udistrital.Citas.service;

import co.edu.udistrital.Citas.entity.Cita;

import java.util.List;

public interface CitaService {
    void crearCita(Cita cita);
    List<Cita> listarPorCalificacion(String calificacion);
    List<Cita> listarPorCedulaBuscador(String cedulaBuscador);
    List<Cita> listar();
}
