package co.edu.udistrital.Citas.repository;

import co.edu.udistrital.Citas.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByCalificacion(String calificacion);
    List<Cita> findByCedulaBuscador(String cedulaBuscador);
    void deleteAll();
}
