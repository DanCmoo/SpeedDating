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
                newRow.insertCell().textContent = formData.get('registroPago');
                newRow.insertCell().textContent = formData.get('name');
                newRow.insertCell().textContent = formData.get('lastname');
                newRow.insertCell().textContent = formData.get('cedula');
                newRow.insertCell().textContent = formData.get('edad');
                newRow.insertCell().textContent = formData.get('estatura');
                newRow.insertCell().textContent = formData.get('p_o');
                newRow.insertCell().textContent = formData.get('contextura');
                newRow.insertCell().textContent = formData.get('estadoCivil');
                newRow.insertCell().textContent = formData.get('genero');
                newRow.insertCell().textContent = formData.get('email');
                newRow.insertCell().textContent = formData.get('telefono');
                newRow.insertCell().textContent = formData.get('gustoContextura');
                newRow.insertCell().textContent = formData.get('gustoInteres');
                newRow.insertCell().textContent = formData.get('gustoEstatura');
                newRow.insertCell().textContent = formData.get('gustoGenero');
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