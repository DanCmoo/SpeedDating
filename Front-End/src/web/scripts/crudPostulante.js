
document.addEventListener("DOMContentLoaded", function() {
    listarPostulantes();
    const btnNuevoPostulante = document.getElementById('nuevoPostulante');
    const modal = document.getElementById('modal');
    const form = document.getElementById('myForm');

    btnNuevoPostulante.addEventListener("click", () => {
        modal.showModal();
    })

    form.addEventListener('submit', (event) => {
        event.preventDefault();
        form.reset();
        modal.close();
    });

    modal.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.close();
        }
    });



});

function guardarPostulante() {
    const datoPago = document.getElementById('registroPago').value;
    let pago = false;
    if(datoPago=="si"){
        pago = true;
    }
    const nombre = document.getElementById('name').value;
    const apellido = document.getElementById('lastname').value;
    const cedula = document.getElementById('cedula').value;
    const edad = document.getElementById('edad').value;
    const estatura = document.getElementById('estatura').value;
    const profesion = document.getElementById('p_o').value;
    const contextura = document.getElementById('contextura').value;
    const estadoCivil = document.getElementById('estadoCivil').value;
    const genero = document.getElementById('genero').value;
    const correo = document.getElementById('email').value;
    const telefono = document.getElementById('telefono').value;
    const interes = document.getElementById('interesPersonal').value;
    const datoDisponibilidad = document.getElementById('disponibilidad').value;
    let disponibilidad = false;
    if(datoDisponibilidad=="si"){
        disponibilidad = true;
    }

    // Crear un objeto con los datos del formulario
    const formData = {
        pago: pago,
        nombre: nombre,
        apellido: apellido,
        cedula: cedula,
        edad: edad,
        estatura: estatura,
        profesion: profesion,
        contextura: contextura,
        estado: estadoCivil,
        identidad: genero,
        correo: correo,
        telefono: telefono,
        interes: interes,
        disponibilidad: disponibilidad,
    };

    fetch('http://localhost:8081/postulante/crear', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
    .then(response => {
        if (response.ok) {
            const tablaPostulantes = document.getElementById('tablaPostulantes');
            tablaPostulantes.innerHTML =`
                <thead>
            <tr class="heading">
                <th>Acción</th>
                <th>Pago</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Cédula</th>
                <th>Edad</th>
                <th>Estatura(metros)</th>
                <th>Profesión/Oficio</th>
                <th>Contextura</th>
                <th>Estado civil</th>
                <th>Género</th>
                <th>Correo</th>
                <th>Teléfono</th>
                <th>Interés principal</th>
                <th>Disponibilidad</th>
            </tr>
            </thead>
            <tbody>
            <tr class="body">

            </tr>

            </tbody>
            `
            listarPostulantes();
            window.alert('El postulante ha sido añadido con exito')
            return response.json();
        } else {
            throw new Error('Error al enviar el postulante');
        }
    })
    .then(data => {
        console.log(data);

    })
    .catch(error => {
        console.error('Error:', error);
    });
}


function listarPostulantes(){
    fetch('http://localhost:8081/postulante/listar')
        .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Error al listar postulantes');
        }
    })
        .then(data => {
        // Limpiar la tabla antes de agregar los nuevos datos
        const tablaPostulantes = document.getElementById('tablaPostulantes');


        // Iterar sobre los datos recibidos y agregar filas a la tabla
        data.forEach(postulante => {
            let datoPago = "No";
            if(postulante.pago){
                datoPago = "Sí";
            }
            let datoDisponibilidad = "No";
            if(postulante.disponibilidad){
                datoDisponibilidad = "Sí";
            }
            const newRow = tablaPostulantes.insertRow();
            newRow.innerHTML = `
                                    <td>
                                        <span class="material-symbols-outlined" onclick="editarPostulante(${postulante.cedula})">edit</span>
                                        <span class="material-symbols-outlined" onclick="eliminarPostulante('${postulante.cedula}')">delete</span>
                                        <span class="material-symbols-outlined" onclick="verPostulante(${postulante.cedula})">visibility</span>
                                    </td>
                                    <td>${datoPago}</td>
                                    <td>${postulante.nombre}</td>
                                    <td>${postulante.apellido}</td>
                                    <td>${postulante.cedula}</td>
                                    <td>${postulante.edad}</td>
                                    <td>${postulante.estatura}</td>
                                    <td>${postulante.profesion}</td>
                                    <td>${postulante.contextura}</td>
                                    <td>${postulante.estado}</td>
                                    <td>${postulante.identidad}</td>
                                    <td>${postulante.correo}</td>
                                    <td>${postulante.telefono}</td>
                                    <td>${postulante.interes}</td>
                                    <td>${datoDisponibilidad}</td>

                                `;


        });
    })
        .catch(error => {
        console.error('Error:', error);
    });

}

function eliminarPostulante(cedula){
    fetch(`http://localhost:8081/postulante/${cedula}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
        if (response.ok) {
            const tablaPostulantes = document.getElementById('tablaPostulantes');
            tablaPostulantes.innerHTML = `
<thead>
            <tr class="heading">
                <th>Acción</th>
                <th>Pago</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Cédula</th>
                <th>Edad</th>
                <th>Estatura(metros)</th>
                <th>Profesión/Oficio</th>
                <th>Contextura</th>
                <th>Estado civil</th>
                <th>Género</th>
                <th>Correo</th>
                <th>Teléfono</th>
                <th>Interés principal</th>
                <th>Disponibilidad</th>
            </tr>
            </thead>
            <tbody>
            <tr class="body">

            </tr>

            </tbody>
            `
            listarPostulantes();
            window.alert('El postulante se ha eliminado correctamente');
        } else {
            console.error('Ocurrió un error al intentar eliminar el postulante');
        }
    })
        .catch(error => {
        console.error('Hubo un problema con la solicitud:', error);
    });

}