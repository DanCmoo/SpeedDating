function generarCitas(){
    fetch('http://localhost:8082/citas/generar', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
        if (response.ok) {
            listarCitas();
            window.alert('Se han generado las citas correctamente');
            return response.json(); // Convertir la respuesta a JSON si la solicitud es exitosa
        } else {
            throw new Error('Error al obtener los datos');
        }
    })
        .then(data => {
        console.log('Datos obtenidos:', data); // Manejar los datos de respuesta
        // Aquí puedes hacer lo que quieras con los datos obtenidos, como mostrarlos en el frontend
    })
        .catch(error => {
        console.error('Error:', error); // Manejar los errores de la solicitud
    });

}
function listarCitas(){
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        slotDuration: '00:10:00',
        defaultTimedEventDuration: '00:10:00',
        slotMinTime: '08:00:00',
        slotMaxTime: '17:00:00',
        initialView: 'dayGridFourWeek',
        views: {
            dayGridFourWeek: {
                type: 'timeGridWeek',
                duration: { days: 5 }
            }
        }

    });
    calendar.render();
    fetch('http://localhost:8082/citas/listar', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
        if (response.ok) {
            return response.json(); // Convertir la respuesta a JSON si la solicitud es exitosa
        } else {
            throw new Error('Error al obtener los datos');
        }
    })
        .then(data => {
        data.forEach(cita=>{
            const title = 'Cita de ' +cita.buscador+' y '+cita.postulante;
            calendar.addEvent({
                title : title,
                start: cita.fecha,
            });


        })

    })
        .catch(error => {
        console.error('Error:', error); // Manejar los errores de la solicitud
    });
}
function sumarMinutosISO(fechaISO, minutos) {
    let fecha = new Date(fechaISO);
    fecha.setMinutes(fecha.getMinutes() + minutos);
    return fecha.toISOString();
}