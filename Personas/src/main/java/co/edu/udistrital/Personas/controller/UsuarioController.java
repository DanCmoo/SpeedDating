package co.edu.udistrital.Personas.controller;

import co.edu.udistrital.Personas.entity.Buscador;
import co.edu.udistrital.Personas.service.BuscadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private BuscadorService buscadorService;

    @PostMapping("/crear")
    public void registrarBuscador(@RequestBody Buscador buscador){
        buscadorService.crearBuscador(buscador);
    }
}
