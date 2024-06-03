package co.edu.udistrital.Citas.service;

import co.edu.udistrital.Citas.entity.Buscador;
import co.edu.udistrital.Citas.entity.Postulante;

import java.util.List;

public interface ParticipanteService {
    List<Buscador> obtenerBuscadores();
    List<Postulante> obtenerPostulante();
}
