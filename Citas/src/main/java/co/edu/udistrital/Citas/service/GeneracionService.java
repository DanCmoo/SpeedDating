package co.edu.udistrital.Citas.service;

import co.edu.udistrital.Citas.entity.Buscador;
import co.edu.udistrital.Citas.entity.Cita;
import co.edu.udistrital.Citas.entity.Postulante;

import java.util.List;

public interface GeneracionService {
    List<Cita> emparejarCitas(List<Buscador> buscadores, List<Postulante> postulantes);
    List<Cita> calificarCitas(List<Cita> citas);

    List<Cita> asignarHorarios(List<Cita> citas);
}
