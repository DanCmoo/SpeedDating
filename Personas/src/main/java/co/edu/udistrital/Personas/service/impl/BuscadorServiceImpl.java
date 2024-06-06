package co.edu.udistrital.Personas.service.impl;

import co.edu.udistrital.Personas.entity.Buscador;
import co.edu.udistrital.Personas.repository.BuscadorRepository;
import co.edu.udistrital.Personas.service.BuscadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Indica que esta clase es un servicio de Spring
@Service
public class BuscadorServiceImpl implements BuscadorService {

    // Inyección de la dependencia BuscadorRepository
    @Autowired
    private BuscadorRepository buscadorRepository;

    // Método para crear un nuevo Buscador
    @Override
    public void crearBuscador(Buscador buscador) {
        // Guarda el nuevo buscador en la base de datos
        buscadorRepository.save(buscador);
    }

    // Método para consultar un Buscador por su cédula
    @Override
    public Buscador consultarBuscador(String cedula) {
        // Busca el buscador por su cédula. Si no lo encuentra, retorna null
        return buscadorRepository.findById(cedula).orElse(null);
    }

    // Método para actualizar un Buscador
    @Override
    public void actualizarBuscador(String cedula, Buscador buscador) {
        // Busca el buscador en la base de datos por su cédula
        Buscador buscadorDB = buscadorRepository.findById(cedula).orElse(null);
        if (buscadorDB != null) {
            // Actualiza los campos del buscador
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
            // Guarda los cambios en la base de datos
            buscadorRepository.save(buscadorDB);
        }
    }

    // Método para eliminar un Buscador por su cédula
    @Override
    public boolean eliminarBuscador(String cedula) {
        // Elimina el buscador por su cédula
        buscadorRepository.deleteById(cedula);
        // Verifica si el buscador aún existe en la base de datos
        return buscadorRepository.existsById(cedula);
    }

    // Método para listar todos los Buscadores
    @Override
    public List<Buscador> listar() {
        // Retorna una lista de todos los buscadores en la base de datos
        return buscadorRepository.findAll();
    }

    // Método para listar Buscadores por estatura
    @Override
    public List<Buscador> listarPorEstatura(float estatura) {
        // Busca y retorna una lista de buscadores por su estatura
        return buscadorRepository.findByEstatura(estatura);
    }
}

