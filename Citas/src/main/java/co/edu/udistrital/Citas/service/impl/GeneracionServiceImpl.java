package co.edu.udistrital.Citas.service.impl;

import co.edu.udistrital.Citas.entity.Buscador;
import co.edu.udistrital.Citas.entity.Cita;
import co.edu.udistrital.Citas.entity.Postulante;
import co.edu.udistrital.Citas.service.GeneracionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GeneracionServiceImpl implements GeneracionService {

    /**
     * Empareja buscadores y postulantes para generar citas basadas en sus preferencias.
     *
     * @param buscadores  La lista de buscadores disponibles.
     * @param postulantes La lista de postulantes disponibles.
     * @return Una lista de citas generadas.
     */
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
                    citas.add(new Cita(buscador.getCedula(), buscador.getNombre(), postulante.getCedula(), postulante.getNombre()));
                }
            }
        }
        return citas;
    }

    /**
     * Asigna calificaciones aleatorias a las citas.
     *
     * @param citas La lista de citas a calificar.
     * @return La lista de citas con calificaciones asignadas.
     */
    @Override
    public List<Cita> calificarCitas(List<Cita> citas) {
        String[] calificacion = {"No conexión", "Amistad", "Más que amistad"};
        Random randomBuscador = new Random(23);
        Random randomPostulante = new Random(369);
        for (Cita cita : citas) {
            String calificacionBuscador = calificacion[randomBuscador.nextInt(3)];
            cita.setCalificacionBuscador(calificacionBuscador);
            String calificacionPostulante = calificacion[randomPostulante.nextInt(3)];
            cita.setCalificacionPostulante(calificacionPostulante);
            if (calificacionPostulante.equals(calificacionBuscador)) {
                cita.setCalificacion(calificacionBuscador);
            } else {
                cita.setCalificacion(calificacion[0]);
            }
        }
        return citas;
    }

    /**
     * Asigna horarios a las citas de forma secuencial.
     *
     * @param citas La lista de citas a las que se les asignará horario.
     * @return La lista de citas con horarios asignados.
     */
    @Override
    public List<Cita> asignarHorarios(List<Cita> citas) {
        LocalDate currentDate = LocalDate.now().plusDays(1); // Comenzar desde el día siguiente al actual
        LocalTime startTime = LocalTime.of(8, 0); // Comenzar a las 8:00 AM
        LocalTime endTime = LocalTime.of(17, 0); // Terminar a las 5:00 PM
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        LocalDateTime horarioInicial = LocalDateTime.of(currentDate, startTime);

        for (Cita cita : citas) {
            LocalDateTime horarioCita = horarioInicial;

            // Encuentra la próxima hora disponible para la cita
            while (horarioCita.toLocalTime().isAfter(endTime) || horarioCita.toLocalTime().equals(endTime)) {
                currentDate = currentDate.plusDays(1);
                horarioCita = LocalDateTime.of(currentDate, startTime);
            }

            cita.setFecha(horarioCita.format(formatter));
            horarioInicial = horarioCita.plusMinutes(10);
        }

        return citas;
    }

}
