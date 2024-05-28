package co.edu.udistrital.Personas.service.impl;

import co.edu.udistrital.Personas.entity.Buscador;
import co.edu.udistrital.Personas.repository.BuscadorRepository;
import co.edu.udistrital.Personas.service.BuscadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscadorServiceImpl implements BuscadorService {
    @Autowired
    private BuscadorRepository buscadorRepository;

    @Override
    public void crearBuscador(Buscador buscador) {
        buscadorRepository.save(buscador);
    }

    @Override
    public Buscador consultarBuscador(String cedula) {
        return buscadorRepository.findById(cedula).orElse(null);
    }

    @Override
    public void actualizarBuscador(String cedula, Buscador buscador) {
        Buscador buscadorDB = buscadorRepository.findById(cedula).orElse(null);
        if (buscadorDB != null) {
            buscadorDB.setNombre(buscador.getNombre());
            buscadorDB.setApellido(buscador.getApellido());
            buscadorDB.setEdad(buscador.getEdad());
            buscadorDB.setEstatura(buscador.getEstatura());
            buscadorDB.setProfesion(buscador.getProfesion());
            buscadorDB.setContextura(buscador.getContextura());
            buscadorDB.setEstado(buscador.getEstado());
            buscadorDB.setIdentidad(buscador.getIdentidad());
            buscadorDB.setCorreo(buscador.getCorreo());
            buscadorDB.setTelefono(buscador.getTelefono());
            buscadorDB.setGustoContextura(buscador.getGustoContextura());
            buscadorDB.setGustoEdad(buscador.getGustoEdad());
            buscadorDB.setGustoEstatura(buscador.getGustoEstatura());
            buscadorDB.setGustoIdentidad(buscador.getGustoIdentidad());
            buscadorDB.setGustoInteres(buscador.getGustoInteres());
            buscadorDB.setPago(buscador.isPago());
            buscadorRepository.save(buscadorDB);
        }


    }

    @Override
    public void eliminarBuscador(String cedula) {
        buscadorRepository.deleteById(cedula);

    }
}
