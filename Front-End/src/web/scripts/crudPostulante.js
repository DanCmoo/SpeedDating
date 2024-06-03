const btnNuevoBuscador = document.querySelector("#nuevoPostulante");
const btnGuardar = document.querySelector("#guardarForm");
const modal = document.querySelector("#modal");
btnNuevoBuscador.addEventListener("click", () => {
    modal.showModal();
})

btnGuardar.addEventListener("click", () =>{
    modal.close();
})