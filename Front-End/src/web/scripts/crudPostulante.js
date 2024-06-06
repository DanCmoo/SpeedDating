document.addEventListener("DOMContentLoaded", function() {
    listarPostulantes();
    const btnNuevoPostulante = document.getElementById('nuevoPostulante');
    const modal = document.getElementById('modal');
    const form = document.getElementById('myForm');
    const form1 = document.getElementById('myForm1');
    const modal1 = document.getElementById('modal1');

    btnNuevoPostulante.addEventListener("click", () => {
        modal.showModal();
    })

    form.addEventListener('submit', (event) => {
        event.preventDefault();
        form.reset();
        modal.close();
    });
    form1.addEventListener('submit', (event) => {
        event.preventDefault();
        editar();
        form1.reset();
        modal1.close();
    });
    modal.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.close();
        }
    });
    document.getElementById('search').addEventListener('keypress', function(event) {
        if (event.key === 'Enter') {
            var cedula = event.target.value;
            buscarPostulante(cedula);
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
                                        <span class="material-symbols-outlined" onclick="editarPostulante('${postulante.cedula}')">edit</span>
                                        <span class="material-symbols-outlined" onclick="eliminarPostulante('${postulante.cedula}')">delete</span>
                                        <span class="material-symbols-outlined" onclick="verPostulante('${postulante.cedula}')">visibility</span>
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
function buscarPostulante(cedula) {
    fetch(`http://localhost:8081/postulante/`+cedula, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
        if (response.ok) {
            return response.json();
        } else if(response.status === 404) {
            window.alert('No se han encontrado postulantes con la cédula proporcionada');
            return []; // Devuelve un array vacío si no se encuentra ningún postulante
        } else {
            throw new Error('Error al obtener los postulantes');
        }
    })
        .then(data => {
        const tbody = document.querySelector('#tablaPostulantes tbody');
        tbody.innerHTML = '';
        if (data.nombre !== undefined) {
            const row = document.createElement('tr');
            let datoPago = "No";
            if (data.pago) {
                datoPago = "Sí";
            }
            let datoDisponibilidad = "No";
            if (data.disponibilidad) {
                datoDisponibilidad = "Sí";
            }
            row.innerHTML = `
                <td>
                    <span class="material-symbols-outlined" onclick="editarPostulante('${data.cedula}')">edit</span>
                    <span class="material-symbols-outlined" onclick="eliminarPostulante('${data.cedula}')">delete</span>
                    <span class="material-symbols-outlined" onclick="verPostulante('${data.cedula}')">visibility</span>
                </td>
                <td>${datoPago}</td>
                <td>${data.nombre}</td>
                <td>${data.apellido}</td>
                <td>${data.cedula}</td>
                <td>${data.edad}</td>
                <td>${data.estatura}</td>
                <td>${data.profesion}</td>
                <td>${data.contextura}</td>
                <td>${data.estado}</td>
                <td>${data.identidad}</td>
                <td>${data.correo}</td>
                <td>${data.telefono}</td>
                <td>${data.interes}</td>
                <td>${datoDisponibilidad}</td>
            `;
            tbody.appendChild(row);
        }
    })
        .catch(error => {
        console.error('Error:', error);
    });

}
function verPostulante(cedula) {
    fetch("http://localhost:8081/postulante/"+cedula, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Error al obtener los datos del postulante');
        }
    })
        .then(data => {
        document.getElementById('name2').value = data.nombre;
        document.getElementById('lastname2').value = data.apellido;
        document.getElementById('cedula2').value = data.cedula;
        document.getElementById('edad2').value = data.edad;
        document.getElementById('estatura2').value = data.estatura;
        document.getElementById('p_o2').value = data.profesion;
        document.getElementById('contextura2').value = data.contextura;
        const disponibilidadSelect = document.getElementById('estadoCivil2');
        for (let i = 0; i < disponibilidadSelect.options.length; i++) {
            if (disponibilidadSelect.options[i].value === data.estado) {
                disponibilidadSelect.selectedIndex = i;
                break;
            }
        }
        document.getElementById('genero2').value = data.identidad;
        document.getElementById('email2').value = data.correo;
        document.getElementById('telefono2').value = data.telefono;
        document.getElementById('interesPersonal2').value = data.interes;
        const dispo = data.disponibilidad;
        let datoDispo = "no";
        if(dispo){
            datoDispo = "si";
        }
        document.getElementById('disponibilidad2').value = datoDispo;


        document.getElementById('modal2').showModal();

    })
        .catch(error => {
        console.error('Error:', error);
    });
}
function editarPostulante(cedula){
    fetch("http://localhost:8081/postulante/"+cedula, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Error al obtener los datos del buscador');
        }
    })
        .then(data => {
        document.getElementById('name1').value = data.nombre;
        document.getElementById('lastname1').value = data.apellido;
        document.getElementById('cedula1').value = data.cedula;
        document.getElementById('edad1').value = data.edad;
        document.getElementById('estatura1').value = data.estatura;
        document.getElementById('p_o1').value = data.profesion;
        document.getElementById('contextura1').value = data.contextura;
        const disponibilidadSelect = document.getElementById('estadoCivil1');
        for (let i = 0; i < disponibilidadSelect.options.length; i++) {
            if (disponibilidadSelect.options[i].value === data.estado) {
                disponibilidadSelect.selectedIndex = i;
                break;
            }
        }
        document.getElementById('genero1').value = data.identidad;
        document.getElementById('email1').value = data.correo;
        document.getElementById('telefono1').value = data.telefono;
        document.getElementById('interesPersonal1').value = data.interes;
        const dispo = data.disponibilidad;
        let datoDispo = "no";
        if(dispo){
            datoDispo = "si";
        }
        document.getElementById('disponibilidad2').value = datoDispo;

        const form = document.getElementById('myForm1');
        form.dataset.editing = 'true';
        form.dataset.cedula = data.cedula;

        document.getElementById('modal1').showModal();
    })
        .catch(error => {
        console.error('Error:', error);
    });
}
function editar(){
    const nombre = document.getElementById('name1').value;
    const apellido = document.getElementById('lastname1').value;
    const edad = document.getElementById('edad1').value;
    const cedula =  document.getElementById('cedula1').value;
    const estatura = document.getElementById('estatura1').value;
    const profesion = document.getElementById('p_o1').value;
    const contextura = document.getElementById('contextura1').value;
    const estadoCivil = document.getElementById('estadoCivil1').value;
    const genero = document.getElementById('genero1').value;
    const correo = document.getElementById('email1').value;
    const telefono = document.getElementById('telefono1').value;
    const interes = document.getElementById('interesPersonal1').value;
    let disponibilidad = false;
    if(document.getElementById('interesPersonal1').value == "si"){
        disponibilidad = true;
    }

    const formData = {
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
    fetch("http://localhost:8081/postulante/"+cedula, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body : JSON.stringify(formData)
    })
        .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Error al obtener los datos del buscador');
        }
    })
        .then(data => {

    })
        .catch(error => {
        console.error('Error:', error);
    });

}
