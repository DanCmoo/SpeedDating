package co.edu.udistrital.Citas.service.impl;

import co.edu.udistrital.Citas.entity.Buscador;
import co.edu.udistrital.Citas.entity.Postulante;
import co.edu.udistrital.Citas.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ParticipanteServiceImpl implements ParticipanteService {

    // Inyección de RestTemplate para realizar llamadas HTTP
    @Autowired
    private RestTemplate restTemplate;

    // Método para obtener la lista de buscadores desde otro servicio
    /**
     * Obtiene la lista de buscadores desde otro servicio.
     *
     * @return La lista de buscadores obtenida del servicio.
     */
    @Override
    public List<Buscador> obtenerBuscadores() {
        // Realiza una llamada HTTP GET al servicio de buscadores y mapea la respuesta a un array de Buscador
        Buscador[] buscadores = restTemplate.getForObject("http://localhost:8081/buscador/listar", Buscador[].class);
        // Verifica que la respuesta no sea nula
        assert buscadores != null;
        // Convierte el array a una lista y la retorna
        return Arrays.asList(buscadores);
    }

    /**
     * Obtiene la lista de postulantes desde otro servicio.
     *
     * @return La lista de postulantes obtenida del servicio.
     */
    @Override
    public List<Postulante> obtenerPostulante() {
        // Realiza una llamada HTTP GET al servicio de postulantes y mapea la respuesta a un array de Postulante
        Postulante[] postulantes = restTemplate.getForObject("http://localhost:8081/postulante/listar", Postulante[].class);
        // Verifica que la respuesta no sea nula
        assert postulantes != null;
        // Convierte el array a una lista y la retorna
        return Arrays.asList(postulantes);
    }

}
