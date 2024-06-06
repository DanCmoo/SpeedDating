package co.edu.udistrital.Citas.controller;

import co.edu.udistrital.Citas.entity.Cita;
import co.edu.udistrital.Citas.service.CitaService;
import co.edu.udistrital.Citas.service.GeneracionService;
import co.edu.udistrital.Citas.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Anotación que define esta clase como un controlador REST
@RestController
// Define la ruta base para las peticiones a este controlador
@RequestMapping("/citas")
public class CitasController {

    // Inyección de dependencias para los servicios necesarios
    @Autowired
    private GeneracionService generacionService;

    @Autowired
    private ParticipanteService participanteService;

    @Autowired
    private CitaService citaService;

    // Endpoint para generar citas
    @GetMapping("/generar")
    public ResponseEntity<String> generarCitas() {
        // Empareja citas entre buscadores y postulantes
        List<Cita> citas = generacionService.emparejarCitas(participanteService.obtenerBuscadores(), participanteService.obtenerPostulante());
        // Asigna horarios a las citas generadas
        citas = generacionService.asignarHorarios(citas);
        // Asigna calificaciones a las citas
        citas = generacionService.calificarCitas(citas);
        // Guarda cada cita generada en la base de datos
        for (Cita cita : citas) {
            citaService.crearCita(cita);
        }
        // Retorna una respuesta indicando que las citas se generaron con éxito
        return ResponseEntity.ok("Se han generado las citas con exito");
    }

    // Endpoint para listar todas las citas
    @GetMapping("/listar")
    public ResponseEntity<List<Cita>> listarCitas() {
        // Obtiene la lista de todas las citas
        List<Cita> citas = citaService.listar();
        // Si no hay citas, retorna una respuesta sin contenido
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        // Si hay citas, las retorna en la respuesta
        return ResponseEntity.ok(citas);
    }

    // Endpoint para listar citas filtradas por calificación
    @GetMapping("/listar/{calificacion}")
    public ResponseEntity<List<Cita>> listarCitasPorCalificacion(@PathVariable String calificacion) {
        // Obtiene la lista de citas filtradas por calificación
        List<Cita> citas = citaService.listarPorCalificacion(calificacion);
        // Si no hay citas con esa calificación, retorna una respuesta sin contenido
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        // Si hay citas, las retorna en la respuesta
        return ResponseEntity.ok(citas);
    }

    // Endpoint para listar citas filtradas por la cédula del buscador
    @GetMapping("/listar/cedula/{cedulaBuscador}")
    public ResponseEntity<List<Cita>> listarPorCedulaBuscador(@PathVariable String cedulaBuscador){
        // Obtiene la lista de citas filtradas por la cédula del buscador
        List<Cita> citas = citaService.listarPorCedulaBuscador(cedulaBuscador);
        // Si no hay citas con esa cédula, retorna una respuesta sin contenido
        if(citas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        // Si hay citas, las retorna en la respuesta
        return ResponseEntity.ok(citas);
    }
}
