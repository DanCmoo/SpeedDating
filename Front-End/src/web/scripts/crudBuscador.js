document.addEventListener("DOMContentLoaded", function() {
    listarBuscadores();
    const nuevoBuscadorBtn = document.getElementById('nuevoBuscador');
    const modal = document.getElementById('modal');
    const form = document.getElementById('myForm');
    const form1 = document.getElementById('myForm1');
    const modal1 = document.getElementById('modal1');

    nuevoBuscadorBtn.addEventListener('click', () => {
        modal.showModal();
    });

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
            buscarBuscador(cedula);
        }
    });


});

function enviarBuscador() {


    // Obtener los valores de los campos del formulario
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
    const gustoContextura = document.getElementById('gustoContextura').value;
    const gustoInteres = document.getElementById('gustoInteres').value;
    const gustoEstatura = document.getElementById('gustoEstatura').value;
    const gustoGenero = document.getElementById('gustoGenero').value;
    const gustoEdad = document.getElementById('gustoEdad').value;

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
        gustoContextura: gustoContextura,
        gustoInteres: gustoInteres,
        gustoEstatura: gustoEstatura,
        gustoIdentidad: gustoGenero,
        gustoEdad: gustoEdad
    };

    // Realizar la solicitud POST utilizando Fetch
    fetch('http://localhost:8081/buscador/crear', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData) // Convertir el objeto a una cadena JSON
    })
        .then(response => {
        if (response.ok) {
            const tablaBuscadores = document.getElementById('tablaBuscadores');
            tablaBuscadores.innerHTML = `
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
                    <th>Gusto contextura</th>
                    <th>Gusto interés</th>
                    <th>Gusto estatura</th>
                    <th>Gusto género</th>
                    <th>Gusto edad</th>
                </tr>
                </thead>
                <tbody>
                    <tr class="body">

                    </tr>

                </tbody>
            `
            listarBuscadores();
            window.alert('El buscador se ha añadido con exito');
            return response.json(); // Convertir la respuesta a JSON si la solicitud es exitosa
        }else if(response.status ===409){
            window.alert('El buscador con la cedula '+cedula+' ya está registrado');

        } else {
            throw new Error('Error al enviar el buscador');
        }
    })
        .then(data => {
        console.log(data); // Manejar los datos de respuesta
    })
        .catch(error => {
        console.error('Error:', error); // Manejar los errores de la solicitud
    });
}

function listarBuscadores() {
    fetch('http://localhost:8081/buscador/listar')
        .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Error al listar buscadores');
        }
    })
        .then(data => {
        // Limpiar la tabla antes de agregar los nuevos datos
        const tbody = document.querySelector('#tablaBuscadores tbody');
        tbody.innerHTML = '';


        // Iterar sobre los datos recibidos y agregar filas a la tabla
        data.forEach(buscador => {
            let datoPago = "No";
            if(buscador.pago){
                datoPago = "Sí";
            }

            const newRow = tablaBuscadores.insertRow();
            newRow.innerHTML = `
                                    <td>
                                        <span class="material-symbols-outlined" onclick="editarBuscador(${buscador.cedula})">edit</span>
                                        <span class="material-symbols-outlined" onclick="eliminarBuscador(${buscador.cedula})">delete</span>
                                        <span class="material-symbols-outlined" onclick="verBuscador(${buscador.cedula})">visibility</span>
                                    </td>
                                    <td>${datoPago}</td>
                                    <td>${buscador.nombre}</td>
                                    <td>${buscador.apellido}</td>
                                    <td>${buscador.cedula}</td>
                                    <td>${buscador.edad}</td>
                                    <td>${buscador.estatura}</td>
                                    <td>${buscador.profesion}</td>
                                    <td>${buscador.contextura}</td>
                                    <td>${buscador.estado}</td>
                                    <td>${buscador.identidad}</td>
                                    <td>${buscador.correo}</td>
                                    <td>${buscador.telefono}</td>
                                    <td>${buscador.gustoContextura}</td>
                                    <td>${buscador.gustoInteres}</td>
                                    <td>${buscador.gustoEstatura}</td>
                                    <td>${buscador.gustoIdentidad}</td>
                                    <td>${buscador.gustoEdad}</td>
                                `;


        });
    })
        .catch(error => {
        console.error('Error:', error);
    });
}

function eliminarBuscador(cedula){

    fetch(`http://localhost:8081/buscador/${cedula}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
        if (response.ok) {
            const tablaBuscadores = document.getElementById('tablaBuscadores');
            tablaBuscadores.innerHTML = `
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
                    <th>Gusto contextura</th>
                    <th>Gusto interés</th>
                    <th>Gusto estatura</th>
                    <th>Gusto género</th>
                    <th>Gusto edad</th>
                </tr>
                </thead>
                <tbody>
                    <tr class="body">

                    </tr>

                </tbody>
            `
            listarBuscadores();
            window.alert('El buscador se ha eliminado correctamente')
        } else {
            console.error('Ocurrió un error al intentar eliminar el buscador');
        }
    })
        .catch(error => {
        console.error('Hubo un problema con la solicitud:', error);
    });


}

function buscarBuscador(cedula) {
    fetch(`http://localhost:8081/buscador/`+cedula, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
        if (response.ok) {
            return response.json();
        }else if(response.status ===404){
            window.alert('No se han encontrado buscadores con la cedula suministrada');
            return [];
        } else {
            throw new Error('Error al obtener los buscadores');
        }
    })
        .then(data => {
        const tbody = document.querySelector('#tablaBuscadores tbody');
        tbody.innerHTML = '';
        if(data.nombre !== undefined){
            const row = document.createElement('tr');
            let datoPago = "No";
            if(data.pago){
                datoPago = "Sí";
            }
            row.innerHTML = `
                    <td>
                                        <span class="material-symbols-outlined" onclick="editarBuscador(${data.cedula})">edit</span>
                                        <span class="material-symbols-outlined" onclick="eliminarBuscador(${data.cedula})">delete</span>
                                        <span class="material-symbols-outlined" onclick="verBuscador(${data.cedula})">visibility</span>
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
                                    <td>${data.gustoContextura}</td>
                                    <td>${data.gustoInteres}</td>
                                    <td>${data.gustoEstatura}</td>
                                    <td>${data.gustoIdentidad}</td>
                                    <td>${data.gustoEdad}</td>
                `;
            tbody.appendChild(row);
        }



    })
        .catch(error => {
        console.error('Error:', error);
    });
}

function editarBuscador(cedula) {
    fetch("http://localhost:8081/buscador/"+cedula, {
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
        document.getElementById('estadoCivil1').value = data.estado;
        document.getElementById('genero1').value = data.identidad;
        document.getElementById('email1').value = data.correo;
        document.getElementById('telefono1').value = data.telefono;
        document.getElementById('gustoContextura1').value = data.gustoContextura;
        document.getElementById('gustoInteres1').value = data.gustoInteres;
        document.getElementById('gustoEstatura1').value = data.gustoEstatura;
        document.getElementById('gustoGenero1').value = data.gustoIdentidad;
        document.getElementById('gustoEdad1').value = data.gustoEdad;

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
    const cedula = document.getElementById('cedula1').value;
    const edad = document.getElementById('edad1').value;
    const estatura = document.getElementById('estatura1').value;
    const profesion = document.getElementById('p_o1').value;
    const contextura = document.getElementById('contextura1').value;
    const estadoCivil = document.getElementById('estadoCivil1').value;
    const genero = document.getElementById('genero1').value;
    const correo = document.getElementById('email1').value;
    const telefono = document.getElementById('telefono1').value;
    const gustoContextura = document.getElementById('gustoContextura1').value;
    const gustoInteres = document.getElementById('gustoInteres1').value;
    const gustoEstatura = document.getElementById('gustoEstatura1').value;
    const gustoGenero = document.getElementById('gustoGenero1').value;
    const gustoEdad = document.getElementById('gustoEdad1').value;
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
        gustoContextura: gustoContextura,
        gustoInteres: gustoInteres,
        gustoEstatura: gustoEstatura,
        gustoIdentidad: gustoGenero,
        gustoEdad: gustoEdad
    };
    fetch("http://localhost:8081/buscador/"+cedula, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
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
    const tbody = document.querySelector('#tablaBuscadores tbody');
    tbody.innerHTML = '';

}

function verBuscador(cedula) {
    fetch("http://localhost:8081/buscador/"+cedula, {
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
        document.getElementById('name2').value = data.nombre;
        document.getElementById('lastname2').value = data.apellido;
        document.getElementById('cedula2').value = data.cedula;
        document.getElementById('edad2').value = data.edad;
        document.getElementById('estatura2').value = data.estatura;
        document.getElementById('p_o2').value = data.profesion;
        document.getElementById('contextura2').value = data.contextura;
        document.getElementById('estadoCivil2').value = data.estado;
        document.getElementById('genero2').value = data.identidad;
        document.getElementById('email2').value = data.correo;
        document.getElementById('telefono2').value = data.telefono;
        document.getElementById('gustoContextura2').value = data.gustoContextura;
        document.getElementById('gustoInteres2').value = data.gustoInteres;
        document.getElementById('gustoEstatura2').value = data.gustoEstatura;
        document.getElementById('gustoGenero2').value = data.gustoIdentidad;
        document.getElementById('gustoEdad2').value = data.gustoEdad;

        document.getElementById('modal2').showModal();
    })
        .catch(error => {
        console.error('Error:', error);
    });
}
