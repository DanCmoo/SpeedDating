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

@RestController
@RequestMapping("/citas")
public class CitasController {
    @Autowired
    private GeneracionService generacionService;
    @Autowired
    private ParticipanteService participanteService;
    @Autowired
    private CitaService citaService;

    @GetMapping("/generar")
    public ResponseEntity<String> generarCitas() {
        List<Cita> citas = generacionService.emparejarCitas(participanteService.obtenerBuscadores(), participanteService.obtenerPostulante());
        citas = generacionService.asignarHorarios(citas);
        citas = generacionService.calificarCitas(citas);
        for (Cita cita : citas) {
            citaService.crearCita(cita);
        }
        return ResponseEntity.ok("Se han generado las citas con exito");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cita>> listarCitas() {
        List<Cita> citas = citaService.listar();
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/listar/{calificacion}")
    public ResponseEntity<List<Cita>> listarCitasPorCalificacion(@PathVariable String calificacion) {
        List<Cita> citas = citaService.listarPorCalificacion(calificacion);
        if (citas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/listar/cedula/{cedulaBuscador}")
    public ResponseEntity<List<Cita>> listarPorCedulaBuscador(@PathVariable String cedulaBuscador){
        List<Cita> citas = citaService.listarPorCedulaBuscador(cedulaBuscador);
        if(citas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(citas);
    }
}
