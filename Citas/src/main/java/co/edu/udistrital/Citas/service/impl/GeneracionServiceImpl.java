package co.edu.udistrital.Citas.service.impl;

import co.edu.udistrital.Citas.entity.Buscador;
import co.edu.udistrital.Citas.entity.Cita;
import co.edu.udistrital.Citas.entity.Postulante;
import co.edu.udistrital.Citas.service.GeneracionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GeneracionServiceImpl implements GeneracionService {
    @Override
    public List<Cita> emparejarCitas(List<Buscador> buscadores, List<Postulante> postulantes) {
        List<Cita> citas = new ArrayList<>();
        for (Buscador buscador : buscadores) {
            for (Postulante postulante : postulantes) {
                int coincidencias = 0;
                if (buscador.getGustoContextura().equalsIgnoreCase(postulante.getContextura())) {
                    coincidencias++;
                }
                if (buscador.getGustoEdad() == postulante.getEdad()) {
                    coincidencias++;
                }
                if (buscador.getGustoEstatura() == postulante.getEstatura()) {
                    coincidencias++;
                }
                if (buscador.getGustoIdentidad().equalsIgnoreCase(postulante.getIdentidad())) {
                    coincidencias++;
                }
                if (buscador.getGustoInteres().equalsIgnoreCase(postulante.getInteresPrincipal())) {
                    coincidencias++;
                }
                if (coincidencias >= 3) {
                    citas.add(new Cita(buscador.getCedula(),buscador.getNombre(), postulante.getCedula(),postulante.getNombre()));
                }
            }
        }
        return citas;
    }

    @Override
    public List<Cita> calificarCitas(List<Cita> citas) {
        String[] calificacion = {"No conexión", "Amistad", "Más que amistad"};
        Random random = new Random();
        for (Cita cita : citas) {
            String calificacionBuscador = calificacion[random.nextInt(3)];
            cita.setCalificacionBuscador(calificacionBuscador);
            String calificacionPostulante = calificacion[random.nextInt(3)];
            cita.setCalificacionPostulante(calificacionPostulante);
            if (calificacionPostulante.equals(calificacionBuscador)) {
                cita.setCalificacion(calificacionBuscador);
            }else{
                cita.setCalificacion(calificacion[0]);
            }
        }

        return citas;
    }

    @Override
    public List<Cita> asignarHorarios(List<Cita> citas) {
        LocalDateTime horarioInicial = LocalDateTime.now();
        for(Cita cita : citas){
            int i = 0;
            LocalDateTime horarioCita = horarioInicial.plusMinutes(i*10);
            String fecha = horarioCita.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            cita.setFecha(fecha);
        }
        return citas;
    }
}
