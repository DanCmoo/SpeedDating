package co.edu.udistrital.Personas.controller;



import co.edu.udistrital.Personas.entity.Postulante;
import co.edu.udistrital.Personas.service.PostulanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postulante")
public class PostulanteController {

    @Autowired
    private PostulanteService postulanteService;

    @GetMapping("/{cedula}")
    public ResponseEntity<Postulante> consultarPostulante(@PathVariable String cedula) {
        Postulante postulante = postulanteService.consultarPostulante(cedula);
        if (postulante != null) {
            return ResponseEntity.ok(postulante);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<String> registrarPostulante(@RequestBody Postulante postulante) {
        Postulante verificar = postulanteService.consultarPostulante(postulante.getCedula());
        if (verificar == null) {
            postulanteService.crearPostulante(postulante);
            return ResponseEntity.ok("Postulante creado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El postulante ya existe");
        }

    }

    @PutMapping("/{cedula}")
    public ResponseEntity<String> actualizarPostulante(@PathVariable String cedula, @RequestBody Postulante postulanteActualizado) {
        postulanteService.actualizarPostulante(cedula, postulanteActualizado);
        return ResponseEntity.ok("Postulante actualizado exitosamente");
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<String> eliminarPostulante(@PathVariable String cedula) {
        if(!postulanteService.eliminarPostulante(cedula)){
            return ResponseEntity.ok("Se eliminó al postulante con éxito");
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No se pudo eliminar al postulante");
        }


    }

    @GetMapping("/listar")
    public List<Postulante> listarPostulantes(){
        return postulanteService.listar();
    }
}
