package co.edu.udistrital.Personas.controller;

import co.edu.udistrital.Personas.entity.Buscador;
import co.edu.udistrital.Personas.entity.Persona;
import co.edu.udistrital.Personas.service.BuscadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/buscador")
public class BuscadorController {
    @Autowired
    private BuscadorService buscadorService;

    @GetMapping("/{cedula}")
    public ResponseEntity<Buscador> consultarBuscador(@PathVariable String cedula) {
        Buscador buscador = buscadorService.consultarBuscador(cedula);
        if (buscador != null) {
            return ResponseEntity.ok(buscador);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<String> registrarBuscador(@RequestBody Buscador buscador) {
        Buscador verificar = buscadorService.consultarBuscador(buscador.getCedula());
        if (verificar == null) {
            buscadorService.crearBuscador(buscador);
            return ResponseEntity.ok("Buscador creado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El buscador ya existe");
        }

    }

    @PutMapping("/{cedula}")
    public ResponseEntity<String> actualizarBuscador(@PathVariable String cedula, @RequestBody Buscador buscadorActualizado) {
        buscadorService.actualizarBuscador(cedula, buscadorActualizado);
        return ResponseEntity.ok("Buscador actualizado exitosamente");
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<String> eliminarBuscador(@PathVariable String cedula) {
        buscadorService.eliminarBuscador(cedula);
        return ResponseEntity.ok("Se eliminó al buscador con éxito");
    }

    @GetMapping("/listar")
    public List<Buscador> listar(){
        return buscadorService.listar();
    }
    @GetMapping("/listar/{criterio}")
    public ResponseEntity<List<Buscador>> listarPorCriterio(@PathVariable String criterio,@RequestParam float estatura){
        List<Buscador> buscadores = buscadorService.listarPorEstatura(estatura);
        if(buscadores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(buscadores);
    }

}
