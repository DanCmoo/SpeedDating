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
