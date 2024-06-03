package co.edu.udistrital.Citas.repository;

import co.edu.udistrital.Citas.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita,Long> {
}
