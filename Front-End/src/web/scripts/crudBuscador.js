document.addEventListener("DOMContentLoaded", function() {
            const nuevoBuscadorBtn = document.getElementById('nuevoBuscador');
            const modal = document.getElementById('modal');
            const form = document.getElementById('myForm');
            const tablaBuscadores = document.getElementById('tablaBuscadores').getElementsByTagName('tbody')[0];

            nuevoBuscadorBtn.addEventListener('click', () => {
                modal.showModal();
            });

            form.addEventListener('submit', (event) => {
                event.preventDefault();

                const formData = new FormData(form);

                const newRow = tablaBuscadores.insertRow();


                const accionCell = newRow.insertCell();
                accionCell.innerHTML = `
                    <span class="material-symbols-outlined">edit</span>
                    <span class="material-symbols-outlined">delete</span>
                    <span class="material-symbols-outlined">visibility</span>
                `;

                accionCell.classList.add('acciones');
                newRow.insertCell().textContent = formData.get('pago');
                newRow.insertCell().textContent = formData.get('nombre');
                newRow.insertCell().textContent = formData.get('apellido');
                newRow.insertCell().textContent = formData.get('cedula');
                newRow.insertCell().textContent = formData.get('edad');
                newRow.insertCell().textContent = formData.get('estatura');
                newRow.insertCell().textContent = formData.get('profesion');
                newRow.insertCell().textContent = formData.get('contextura');
                newRow.insertCell().textContent = formData.get('estado');
                newRow.insertCell().textContent = formData.get('identidad');
                newRow.insertCell().textContent = formData.get('correo');
                newRow.insertCell().textContent = formData.get('telefono');
                newRow.insertCell().textContent = formData.get('gustoContextura');
                newRow.insertCell().textContent = formData.get('gustoInteres');
                newRow.insertCell().textContent = formData.get('gustoEstatura');
                newRow.insertCell().textContent = formData.get('gustoIdentidad');
                newRow.insertCell().textContent = formData.get('gustoEdad');

                form.reset();
                modal.close();
            });

            modal.addEventListener('click', (event) => {
                if (event.target === modal) {
                    modal.close();
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
                return response.json(); // Convertir la respuesta a JSON si la solicitud es exitosa
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
            const tablaBuscadores = document.getElementById('tablaBuscadores');
            tablaBuscadores.innerHTML = '';

            // Iterar sobre los datos recibidos y agregar filas a la tabla
            data.forEach(buscador => {
                const nuevaFila = tablaBuscadores.insertRow();
                nuevaFila.innerHTML = `
                    <td>Aquí podrías agregar botones para editar/eliminar</td>
                    <td>${buscador.pago}</td>
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