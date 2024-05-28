package co.edu.udistrital.Personas.repository;

import co.edu.udistrital.Personas.entity.Postulante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostulanteRepository extends JpaRepository<Postulante,String> {
}
