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

    /**
     * Consulta un buscador por su número de cédula.
     *
     * @param cedula El número de cédula del buscador a consultar.
     * @return ResponseEntity con el objeto Buscador si se encuentra, o un estado 404 (NOT FOUND) si no se encuentra.
     */
    @GetMapping("/{cedula}")
    public ResponseEntity<Buscador> consultarBuscador(@PathVariable String cedula) {
        Buscador buscador = buscadorService.consultarBuscador(cedula);
        if (buscador != null) {
            return ResponseEntity.ok(buscador);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * Registra un nuevo buscador.
     *
     * @param buscador El objeto Buscador a registrar.
     * @return ResponseEntity con un mensaje indicando el resultado de la operación.
     */
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

    /**
     * Actualiza la información de un buscador existente.
     *
     * @param cedula            El número de cédula del buscador a actualizar.
     * @param buscadorActualizado El objeto Buscador con la información actualizada.
     * @return ResponseEntity con un mensaje indicando el resultado de la operación.
     */
    @PutMapping("/{cedula}")
    public ResponseEntity<String> actualizarBuscador(@PathVariable String cedula, @RequestBody Buscador buscadorActualizado) {
        buscadorService.actualizarBuscador(cedula, buscadorActualizado);
        return ResponseEntity.ok("Buscador actualizado exitosamente");
    }

    /**
     * Elimina un buscador por su número de cédula.
     *
     * @param cedula El número de cédula del buscador a eliminar.
     * @return ResponseEntity con un mensaje indicando el resultado de la operación.
     */
    @DeleteMapping("/{cedula}")
    public ResponseEntity<String> eliminarBuscador(@PathVariable String cedula) {
        if(!buscadorService.eliminarBuscador(cedula)){
            return ResponseEntity.ok("Se eliminó al buscador con éxito");
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No se pudo eliminar al buscador");
        }
    }

    /**
     * Obtiene una lista de todos los buscadores registrados.
     *
     * @return ResponseEntity con la lista de buscadores.
     */
    @GetMapping("/listar")
    public ResponseEntity<List<Buscador>> listar(){
        return ResponseEntity.ok(buscadorService.listar());
    }

    /**
     * Obtiene una lista de buscadores que cumplen con un criterio específico, en este caso, una estatura mínima.
     *
     * @param criterio  El criterio de búsqueda (en este caso, "estatura").
     * @param estatura  La estatura mínima de los buscadores a listar.
     * @return ResponseEntity con la lista de buscadores que cumplen con el criterio.
     */
    @GetMapping("/listar/{criterio}")
    public ResponseEntity<List<Buscador>> listarPorCriterio(@PathVariable String criterio, @RequestParam float estatura){
        List<Buscador> buscadores = buscadorService.listarPorEstatura(estatura);
        if(buscadores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(buscadores);
    }


}
