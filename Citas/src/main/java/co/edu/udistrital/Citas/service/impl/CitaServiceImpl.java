package co.edu.udistrital.Citas.service.impl;

import co.edu.udistrital.Citas.entity.Cita;
import co.edu.udistrital.Citas.repository.CitaRepository;
import co.edu.udistrital.Citas.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {
    @Autowired
    private CitaRepository citaRepository;

    @Override
    public void crearCita(Cita cita) {
        citaRepository.save(cita);
    }

    @Override
    public List<Cita> listarPorCalificacion(String calificacion) {
        return citaRepository.findByCalificacion(calificacion);
    }

    @Override
    public List<Cita> listarPorCedulaBuscador(String cedulaBuscador) {
        return citaRepository.findByCedulaBuscador(cedulaBuscador);
    }

    @Override
    public List<Cita> listar() {
        return citaRepository.findAll();
    }
}
