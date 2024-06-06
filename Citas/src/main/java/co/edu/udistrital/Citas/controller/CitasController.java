package co.edu.udistrital.Citas.controller;

import co.edu.udistrital.Citas.entity.Cita;
import co.edu.udistrital.Citas.service.CitaService;
import co.edu.udistrital.Citas.service.GeneracionService;
import co.edu.udistrital.Citas.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitasController {
    @Autowired
    private GeneracionService generacionService;
    @Autowired
    private ParticipanteService participanteService;
    @Autowired
    private CitaService citaService;

    /**
     * Genera citas entre buscadores y postulantes, las asigna horarios y las califica.
     * Luego, guarda las citas generadas en la base de datos.
     *
     * @return ResponseEntity con un mensaje indicando si las citas se generaron correctamente.
     */
    @GetMapping("/generar")
    public ResponseEntity<String> generarCitas() {
        citaService.eliminarCitas();
        List<Cita> citas = generacionService.emparejarCitas(participanteService.obtenerBuscadores(), participanteService.obtenerPostulante());
        citas = generacionService.asignarHorarios(citas);
        citas = generacionService.calificarCitas(citas);
        for (Cita cita : citas) {
            citaService.crearCita(cita);
        }
        return ResponseEntity.ok("Se han generado las citas con éxito");
    }

    /**
     * Obtiene todas las citas registradas en la base de datos.
     *
     * @return ResponseEntity con la lista de citas si existen, o un ResponseEntity sin contenido si no hay citas.
     */
    @GetMapping("/listar")
    public ResponseEntity<List<Cita>> listarCitas() {
        List<Cita> citas = citaService.listar();
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }

    /**
     * Obtiene todas las citas con una calificación específica.
     *
     * @param calificacion La calificación de las citas a listar.
     * @return ResponseEntity con la lista de citas si existen, o un ResponseEntity sin contenido si no hay citas con esa calificación.
     */
    @GetMapping("/listar/{calificacion}")
    public ResponseEntity<List<Cita>> listarCitasPorCalificacion(@PathVariable String calificacion) {
        List<Cita> citas = citaService.listarPorCalificacion(calificacion);
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }

    /**
     * Obtiene todas las citas de un buscador específico por su número de cédula.
     *
     * @param cedulaBuscador La cédula del buscador cuyas citas se desean listar.
     * @return ResponseEntity con la lista de citas si existen, o un ResponseEntity con estado NOT_FOUND si no hay citas para el buscador.
     */
    @GetMapping("/listar/cedula/{cedulaBuscador}")
    public ResponseEntity<List<Cita>> listarPorCedulaBuscador(@PathVariable String cedulaBuscador) {
        List<Cita> citas = citaService.listarPorCedulaBuscador(cedulaBuscador);
        if (citas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(citas);
    }

}
