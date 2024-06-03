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
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Buscador> obtenerBuscadores() {
        Buscador[] buscadores = restTemplate.getForObject("http://localhost:8081/buscador/listar",Buscador[].class);
        assert buscadores != null;
        return Arrays.asList(buscadores);
    }

    @Override
    public List<Postulante> obtenerPostulante() {
        Postulante[] postulantes = restTemplate.getForObject("http://localhost:8081/postulante/listar",Postulante[].class);
        assert postulantes != null;
        return Arrays.asList(postulantes);
    }
}
