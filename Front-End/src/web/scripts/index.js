function funcionHora(){
    var saludo;
    var hora = new Date().getHours();
    if (hora < 12) {
        saludo = "Buenos días";
    } else if (hora >= 12 && hora < 19) {
        saludo = "Buenas tardes";
    } else {
        saludo = "Buenas noches";
    }
    document.getElementById("saludo").innerHTML = saludo;
}
window.onload = funcionHora;

function enviarCorreo(){
    fetch('http://localhost:8082/citas/listar', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
        if (response.ok) {
            window.alert('Los correos han sido enviados con exito')
            return response.json(); // Convertir la respuesta a JSON si la solicitud es exitosa
        } else {
            throw new Error('Error al obtener los datos');
        }
    })
        .then(data => {
        data.forEach(cita=>{
            let formData = {};
            let promesas = [];

            // Llamada a pedirBuscador
            promesas.push(
                pedirBuscador(cita.cedulaBuscador)
                    .then(buscador => {
                    formData.nombreBuscador = buscador.nombre;
                    formData.correoBuscador = buscador.correo;
                    formData.telefonoBuscador = buscador.telefono;
                })
                    .catch(error => {
                    console.error('Error en pedirBuscador:', error);
                })
            );

            // Llamada a pedirPostulante
            promesas.push(
                pedirPostulante(cita.cedulaPostulante)
                    .then(postulante => {
                    formData.nombrePostulante = postulante.nombre;
                    formData.correoPostulante = postulante.correo;
                    formData.telefonoPostulante = postulante.telefono;
                })
                    .catch(error => {
                    console.error('Error en pedirPostulante:', error);
                })
            );
            Promise.all(promesas)
                .then(() => {

                const calificacion = cita.calificacion;
                if(calificacion == "No conexión"){
                    enviarCorreoAmistad(formData);
                }
                if (calificacion == "Más que amistad"){
                    enviarCorreoMasAmistad(formData);
                }
            })
                .catch(error => {
                console.error('Error en Promise.all:', error);
            });
        })

    })
        .catch(error => {
        console.error('Error:', error); // Manejar los errores de la solicitud
    });

}

function pedirBuscador(cedula){
    return new Promise((resolve,reject)=>{
        fetch('http://localhost:8081/buscador/'+cedula, {
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
                resolve(data);


        })
            .catch(error => {
                reject(error);
        });
    });

}
function pedirPostulante(cedula){
    return new Promise((resolve,reject)=>{
        fetch('http://localhost:8081/postulante/'+cedula, {
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
                resolve(data);


        })
            .catch(error => {
            reject(error);
        });
    });

}

function enviarCorreoAmistad(formData){
    fetch('http://localhost:8080/comunicacion/correo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body : JSON.stringify(formData)
    })
        .then(response => {
        if (response.ok) {
            return response.json(); // Convertir la respuesta a JSON si la solicitud es exitosa
        } else {
            throw new Error('Error al obtener los datos');
        }
    })
        .then(data => {


    })
        .catch(error => {
        console.error('Error:', error); // Manejar los errores de la solicitud
    });

}

function enviarCorreoMasAmistad(formData){
    fetch('http://localhost:8080/comunicacion/correoAmor', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
        if (response.ok) {
            return response.json(); // Convertir la respuesta a JSON si la solicitud es exitosa
        } else {
            throw new Error('Error al obtener los datos');
        }
    })
        .then(data => {


    })
        .catch(error => {
        console.error('Error:', error); // Manejar los errores de la solicitud
    });

}

function pedirPDF(){
    fetch('http://localhost:8083/pdf', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
        if (response.ok) {
            return response.blob(); // Convertir la respuesta a un Blob
        } else {
            throw new Error('Error al obtener el PDF');
        }
    })
        .then(blob => {
        // Crear una URL para el Blob
        const url = window.URL.createObjectURL(blob);

        // Crear un enlace temporal para descargar el PDF
        const a = document.createElement('a');
        a.href = url;
        a.download = 'file.pdf'; // Nombre del archivo PDF para descargar
        document.body.appendChild(a);
        a.click();

        // Eliminar el enlace temporal y revocar la URL del Blob
        a.remove();
        window.URL.revokeObjectURL(url);
    })
        .catch(error => {
        console.error('Error:', error); // Manejar los errores de la solicitud
    });

}