document.addEventListener("DOMContentLoaded", function() {
    // Obtener el valor del parámetro dni de la URL
    var urlParams = new URLSearchParams(window.location.search);
    var dniParam = urlParams.get('dni');

    // Si el parámetro dni está presente, enviar el formulario automáticamente
    if (dniParam) {
        document.getElementById("dniForm").submit();
    }
});