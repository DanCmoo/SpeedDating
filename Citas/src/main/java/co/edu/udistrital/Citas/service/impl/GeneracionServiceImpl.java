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

// Define esta clase como un servicio de Spring
@Service
public class GeneracionServiceImpl implements GeneracionService {

    // Método para emparejar citas entre buscadores y postulantes
    @Override
    public List<Cita> emparejarCitas(List<Buscador> buscadores, List<Postulante> postulantes) {
        List<Cita> citas = new ArrayList<>(); // Lista donde se almacenarán las citas emparejadas
        for (Buscador buscador : buscadores) {
            for (Postulante postulante : postulantes) {
                int coincidencias = 0; // Contador de coincidencias entre buscador y postulante

                // Comparaciones entre gustos del buscador y características del postulante
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

                // Si hay al menos 3 coincidencias, se crea una nueva cita
                if (coincidencias >= 3) {
                    citas.add(new Cita(buscador.getCedula(), buscador.getNombre(), postulante.getCedula(), postulante.getNombre()));
                }
            }
        }
        return citas; // Retorna la lista de citas generadas
    }

    // Método para calificar las citas
    @Override
    public List<Cita> calificarCitas(List<Cita> citas) {
        String[] calificacion = {"No conexión", "Amistad", "Más que amistad"}; // Posibles calificaciones
        Random randomBuscador = new Random(23); // Generador de números aleatorios para calificación del buscador
        Random randomPostulante = new Random(369); // Generador de números aleatorios para calificación del postulante

        for (Cita cita : citas) {
            // Genera una calificación aleatoria para el buscador
            String calificacionBuscador = calificacion[randomBuscador.nextInt(3)];
            cita.setCalificacionBuscador(calificacionBuscador);
            // Genera una calificación aleatoria para el postulante
            String calificacionPostulante = calificacion[randomPostulante.nextInt(3)];
            cita.setCalificacionPostulante(calificacionPostulante);

            // Si las calificaciones coinciden, se establece como la calificación de la cita
            if (calificacionPostulante.equals(calificacionBuscador)) {
                cita.setCalificacion(calificacionBuscador);
            } else {
                cita.setCalificacion(calificacion[0]); // Si no coinciden, se establece "No conexión"
            }
        }

        return citas; // Retorna la lista de citas calificadas
    }

    // Método para asignar horarios a las citas
    @Override
    public List<Cita> asignarHorarios(List<Cita> citas) {
        LocalDate currentDate = LocalDate.now().plusDays(1); // Comienza desde el día siguiente al actual
        LocalTime startTime = LocalTime.of(8, 0); // Comienza a las 8:00 AM
        LocalTime endTime = LocalTime.of(17, 0); // Termina a las 5:00 PM
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // Formato para la fecha y hora

        LocalDateTime horarioInicial = LocalDateTime.of(currentDate, startTime); // Hora inicial para la primera cita

        for (Cita cita : citas) {
            int i = 0; // Contador no utilizado (puede ser eliminado)
            LocalDateTime horarioCita = horarioInicial;

            // Encuentra la próxima hora disponible para la cita
            while (horarioCita.toLocalTime().isAfter(endTime) || horarioCita.toLocalTime().equals(endTime)) {
                currentDate = currentDate.plusDays(1); // Avanza al siguiente día
                horarioCita = LocalDateTime.of(currentDate, startTime); // Resetea la hora al inicio del día
            }

            // Asigna el horario a la cita y actualiza la hora inicial para la próxima cita
            cita.setFecha(horarioCita.format(formatter));
            horarioInicial = horarioCita.plusMinutes(10); // Incrementa 10 minutos para la próxima cita
        }

        return citas; // Retorna la lista de citas con horarios asignados
    }
}

