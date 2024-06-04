const btnNuevoBuscador = document.querySelector("#nuevoPostulante");
const btnGuardar = document.querySelector("#guardarForm");
const modal = document.querySelector("#modal");
btnNuevoBuscador.addEventListener("click", () => {
    modal.showModal();
})

btnGuardar.addEventListener("click", () =>{
    modal.close();
})

function enviarPostulante() {
    const datoPago = document.getElementById('registroPago').value;
    let pago = false;
    if(datoPago=="si"){
        pago = true;
    }

    fetch('http://localhost:8081/postulante/crear', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Error al enviar el postulante');
        }
    })
    .then(data => {
        console.log(data);
        listarPostulantes();
    })
    .catch(error => {
        console.error('Error:', error);
    });
}