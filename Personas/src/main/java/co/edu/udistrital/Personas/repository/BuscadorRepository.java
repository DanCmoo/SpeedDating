package co.edu.udistrital.Personas.repository;

import co.edu.udistrital.Personas.entity.Buscador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuscadorRepository extends JpaRepository<Buscador,String> {

}
