package co.edu.udistrital.Citas.service.impl;

import co.edu.udistrital.Citas.entity.Cita;
import co.edu.udistrital.Citas.repository.CitaRepository;
import co.edu.udistrital.Citas.service.CitaService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private EntityManager entityManager;

    /**
     * Guarda una nueva cita en la base de datos.
     *
     * @param cita La cita a ser guardada.
     */
    @Override
    public void crearCita(Cita cita) {
        citaRepository.save(cita);
    }

    /**
     * Obtiene todas las citas con una calificación específica.
     *
     * @param calificacion La calificación de las citas a listar.
     * @return Una lista de citas con la calificación especificada.
     */
    @Override
    public List<Cita> listarPorCalificacion(String calificacion) {
        return citaRepository.findByCalificacion(calificacion);
    }

    /**
     * Obtiene todas las citas asociadas a un buscador específico por su número de cédula.
     *
     * @param cedulaBuscador La cédula del buscador cuyas citas se desean listar.
     * @return Una lista de citas asociadas al buscador especificado.
     */
    @Override
    public List<Cita> listarPorCedulaBuscador(String cedulaBuscador) {
        return citaRepository.findByCedulaBuscador(cedulaBuscador);
    }

    /**
     * Obtiene todas las citas registradas en la base de datos.
     *
     * @return Una lista de todas las citas registradas.
     */
    @Override
    public List<Cita> listar() {
        return citaRepository.findAll();
    }

    /**
     * Elimina todas las citas de la base de datos y reinicia el contador de identificación de citas.
     * Se realiza dentro de una transacción para garantizar la consistencia de los datos.
     */
    @Transactional
    @Override
    public void eliminarCitas() {
        citaRepository.deleteAll();
        entityManager.createNativeQuery("ALTER TABLE citas AUTO_INCREMENT = 1").executeUpdate();
    }

}
