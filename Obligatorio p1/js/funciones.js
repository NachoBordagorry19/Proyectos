//Juan Ignacio Bordagorry (328149)
//Joaquín Gonzales (322133)



window.addEventListener("load", inicio);
let miSistema = new Sistema();
let listaPreguntas = [];
function inicio() {
    if (confirm("¿Quiere ingresar datos?")) {
        ingresarDatos();
        document.getElementById("parte1").style.display = "block";
        document.getElementById("part1").style.display = "block";
    }
    else {
        alert("No se ingresaran datos");
        document.getElementById("parte1").style.display = "block";
        document.getElementById("part1").style.display = "block";
    }
    document.getElementById("link1").addEventListener("click", Contenido1);
    document.getElementById("link2").addEventListener("click", Contenido2);
    document.getElementById("link3").addEventListener("click", Contenido3);
    document.getElementById("botton").addEventListener("click", agregarTema);
    document.getElementById("boton2").addEventListener("click", agregoPregunta);
    document.getElementById("boton3").addEventListener("click", juega);
    document.getElementById("botonAyuda").addEventListener("click", ayuda);
    document.getElementById("botonSgpregunta").addEventListener("click", siguientePregunta);
    document.getElementById("botonTerminar").addEventListener("click", terminar);
}
//funcion que ingresa los datosPrueba
function ingresarDatos() {
    for (let i = 0; i < preguntas.length; i++) {
        let textoPregunta = preguntas[i].texto;
        let respuestaCorrecta = preguntas[i].respuestaCorrecta;
        let respuestaIncorecta = preguntas[i].respuestasIncorrectas;
        let nivel = preguntas[i].nivel;
        let tema = new Tema(preguntas[i].tema.nombre, preguntas[i].tema.descripcion);
        let pregunta = new Pregunta(tema, nivel, textoPregunta, respuestaCorrecta, respuestaIncorecta)
        miSistema.agregarTema(tema);
        miSistema.agregarPregunta(pregunta);
        agregarTemaAlDOM(tema);
        agregarTemaAlSelect(tema);
    }
    sumarNumeroPregunta();
    sumarNumeroTema();
    promedioTema();
    agregarAtabla();
    agregarAtema();
}



//funciones usadas para mostrar o ocultar secciones
function mostrar(id) {
    document.getElementById(id).style.display = "block";
}

function ocultar(id) {
    document.getElementById(id).style.display = "none"

}

function Contenido1() {
    mostrar("part1");
    ocultar("parte2");
    ocultar("parte3");

}

function Contenido2() {
    mostrar("parte2");
    ocultar("parte3");
    ocultar("part1");


}

function Contenido3() {
    mostrar("parte3");
    mostrar("Ajugar");
    ocultar("part1");
    ocultar("parte2");
    ocultar("juega");
    document.getElementById("NivelJuego").disabled = false;
    document.getElementById("temaAelegir").disabled = false;
    document.getElementById("boton3").disabled = false;
    document.getElementById("botonSgpregunta").disabled = false;
    cantidad = 0;
    actualizarPuntaje();
}
//funcion que agrega tema
function agregarTema() {
    let miForm = document.getElementById("FormularioTemas");
    if (miForm.reportValidity()) {
        let tema = document.getElementById("nombre").value;
        let descripcion = document.getElementById("descripcion").value;
        let tem = new Tema(tema, descripcion);
        let exito = miSistema.agregarTema(tem);
        if (!exito) {
            miForm.reset();
        }
        else {
            sumarNumeroTema();
            agregarTemaAlDOM();
            agregarTemaAlSelect();
            promedioTema();
            temaConPregunta();
            miForm.reset();

        }
    }
}
//funcion que realiza la suma de temas
function sumarNumeroTema() {
    let numeroTema = document.getElementById("numeroTema");
    let cant = miSistema.sumarNumero();
    numeroTema.textContent = "Lista de temas (total de temas: " + cant + ")";
}
//funcion que muestra el tema en la pagina
function agregarTemaAlDOM() {
    let listatemas = document.getElementById("listatemas");
    listatemas.innerHTML = "";
    for (let i = 0; i < miSistema.listaTemas.length; i++) {
        let li = document.createElement("li");
        li.textContent = miSistema.listaTemas[i].nombre + ":" + miSistema.listaTemas[i].descripcion;
        listatemas.appendChild(li);
    }
}
//funcion que agrega el tema al select de preguntas
function agregarTemaAlSelect() {
    let lista = miSistema.listaTemas;
    let select = document.getElementById("tema");
    select.innerHTML = "";
    for (let i = 0; i < lista.length; i++) {
        let option = document.createElement("option");
        option.value = lista[i].nombre;
        option.text = lista[i].nombre;
        select.appendChild(option);
    }
}
//funcion que realiza promedio entre preguntas y temas
function promedioTema() {
    let numero = document.getElementById("promedioTemas");
    let preguntas = miSistema.listaPreguntas.length;
    let temas = 0;
    for (let i = 0; i < miSistema.listaTemas.length; i++) {
        if (miSistema.listaTemas[i].nombre) {
            temas++;
        }
    }
    let resultado = preguntas / temas;
    let resultadoComas = resultado.toFixed(2);
    numero.textContent = "Promedio de preguntas por tema (cantidad total de preguntas/cantidad total de temas): " + resultadoComas;
}
//funcion encargada de agregar pregunta
function agregoPregunta() {
    let miForm = document.getElementById("FormularioPreguntas");
    let select = document.getElementById("tema");
    select.setAttribute("required","required");
    let select2 = document.getElementById("temaAelegir");
    select2.setAttribute("required","required");
    if (miForm.reportValidity()) {
        let tema = miSistema.getTema(document.getElementById("tema").value);
        let nivel = document.getElementById("nivel").value;
        let textoPregunta = document.getElementById("textopregunta").value;
        let respuestaCorrecta = document.getElementById("correcta").value;
        let respuestaIncorecta = document.getElementById("incorrecta").value;
        let pregunta = new Pregunta(tema, nivel, textoPregunta, respuestaCorrecta, respuestaIncorecta.split(","));
        let verifica = verifico(respuestaCorrecta, respuestaIncorecta);
        if (!verifica) {
            alert("no se puede incluir la respuesta correcta en las incorrectas");
            miForm.reset();
            return false;
        }
        else {
            miSistema.agregarPregunta(pregunta);
            temaConPregunta();
            sumarNumeroPregunta();
            miForm.reset();
            promedioTema();
            agregarAtabla(pregunta);
            agregarAtema();
        }
    }
}
//funcion encargada de poner los temas sin preguntas
function temaConPregunta() {
    let temasSinPreguntas = document.getElementById("temasSinPreguntas");
    temasSinPreguntas.innerHTML = "";
    let preguntas = miSistema.listaPreguntas;
    let temas = miSistema.listaTemas;

    for (let i = 0; i < temas.length; i++) {
        let temaT = temas[i].nombre;
        let descripcionT = temas[i].descripcion;
        let tienePregunta = false;
        for (let j = 0; j < preguntas.length; j++) {
            let temaP = preguntas[j].tema.nombre;
            if (temaT == temaP) {
                tienePregunta = true;
            }
        }
        if (!tienePregunta) {
            let li = document.createElement("li");
            li.textContent = temaT + ":" + descripcionT;
            temasSinPreguntas.appendChild(li);
        }
    }
    if (temasSinPreguntas.childElementCount === 0) {
        let p = document.createElement("p");
        p.textContent = "Sin datos";
        temasSinPreguntas.appendChild(p);
    }

}
//funcion encargada de asegurarse que la respuesta correcta no este en la incorrecta
function verifico(respuestaCorrecta, respuestaIncorecta) {  
    let ok = true
    if (respuestaIncorecta.includes(respuestaCorrecta)) {
        ok = false;
    }
    else {
        return ok;
    }
}
//funcion encargada de actualizar la cantidad de preguntas 
function sumarNumeroPregunta() {
    let numeroPregunta = document.getElementById("numeroPregunta");
    let cant = miSistema.sumarNumeroPregunta();
    numeroPregunta.textContent = "Total de preguntas registradas: " + cant + " preguntas";
}

let red = 255;
let blue = 0;
let green = 255;
function generarColor(aRed, aGreen, aBlue) {
    red = red + aRed;
    blue = blue + aBlue;
    green = green + aGreen;
    return "rgb(" + red + "," + green + "," + blue + ")"
}
//funcion que agrega pregunta a la tabla
function agregarAtabla() {
    red = 255;
    blue = 0;
    green = 255;
    let tabla = document.getElementById("tabla");
    tabla.innerHTML = "";
    miSistema.listaPreguntas.sort(ordenarTablaCreciente);
    miSistema.listaPreguntas.sort(ordenarTablaDecreciente);
    let misPreguntas = miSistema.listaPreguntas;
    let ultimoTema = "";
    let color = "";

    for (let i = 0; i < misPreguntas.length; i++) {
        let tr = document.createElement("tr");
        let tema = document.createElement("td");

        if (ultimoTema != misPreguntas[i].tema.nombre) {
            color = generarColor(-15, -25, 0);
            ultimoTema = misPreguntas[i].tema.nombre;

        }
        misPreguntas[i].tema.color = color;
        tr.style.background = color;

        let nivel = document.createElement("td");
        let textoPregunta = document.createElement("td");
        let respuestaCorrecta = document.createElement("td");
        let respuestaIncorecta = document.createElement("td");
        tema.textContent = misPreguntas[i].tema.nombre;
        nivel.textContent = misPreguntas[i].nivel;
        textoPregunta.textContent = misPreguntas[i].textoPregunta;
        respuestaCorrecta.textContent = misPreguntas[i].respuestaCorrecta;
        respuestaIncorecta.textContent = misPreguntas[i].respuestaIncorecta;
        tr.appendChild(tema);
        tr.appendChild(nivel);
        tr.appendChild(textoPregunta);
        tr.appendChild(respuestaCorrecta);
        tr.appendChild(respuestaIncorecta);
        tabla.appendChild(tr);
    }

}
//funcion que ordena tabla manera 1
function ordenarTablaCreciente(a, b) {
    let inputRadioUno = document.getElementById("seleccion");
    if (inputRadioUno.checked) {
        if (a.tema.nombre === b.tema.nombre) {
            return a.nivel - b.nivel;
        }
        return a.tema.nombre.localeCompare(b.tema.nombre);
    }


}
//funcion que ordena tabla manera 2
function ordenarTablaDecreciente(a, b) {
    let inputRadioDos = document.getElementById("seleccion2");
    if (inputRadioDos.checked) {
        if (b.tema.nombre === a.tema.nombre) {
            return a.nivel - b.nivel;
        }
        return b.tema.nombre.localeCompare(a.tema.nombre);
    }
}
//funcion que se encarga de agregar el tema al select de jugar
function agregarAtema() {
    let select = document.getElementById("temaAelegir");
    select.innerHTML = "";
    let contiene = [];
    for (let i = 0; i < miSistema.listaPreguntas.length; i++) {
        let tema = miSistema.listaPreguntas[i].tema.nombre;
        if (!contiene.includes(tema)) {
            contiene.push(tema);
            let = option = document.createElement("option");
            option.value = tema;
            option.textContent = tema;
            select.appendChild(option);
        }
    }
}
//funcion que se da al apretar el boton jugar
function juega() {
    let nivel = document.getElementById("NivelJuego").value;
    let nombreTema = document.getElementById("temaAelegir").value;
    document.getElementById("NivelJuego").disabled = true;
    document.getElementById("temaAelegir").disabled = true;
    document.getElementById("boton3").disabled = true;
    listaPreguntas = miSistema.listaPreguntasTema(nombreTema, nivel);


    if (listaPreguntas.length == 0) {
        alert("El tema elegido no contiene ese nivel");
        document.getElementById("temaAelegir").disabled = false;
        document.getElementById("NivelJuego").disabled = false;
        document.getElementById("boton3").disabled = false;
    }
    else {
        mostrar("parte3");
        ocultar("parte2");
        ocultar("part1");
        mostrar("juega");
        mostrar("Ajugar");

        let pregunta = proximaPregunta();
        let indice = listaPreguntas.indexOf(pregunta);
        listaPreguntas.splice(indice, 1);
        mostrarPregunta(pregunta);
        this.disabled = true;
    }
}

//funcion que da pregunta random
function proximaPregunta() {
    let indice = Math.floor(Math.random() * listaPreguntas.length);
    return listaPreguntas[indice];
}

//funcion que muestra pregunta en el DOM
function mostrarPregunta(pregunta) {
    let textoPregunta = document.getElementById("textoPregunta");
    textoPregunta.style.background = pregunta.tema.color;
    let botones = document.getElementById("botonesDeRespuestas");
    textoPregunta.innerHTML = pregunta.textoPregunta;
    botones.innerHTML = "";
    let respuestaIncorecta = pregunta.respuestaIncorecta;
    let respuestaCorrecta = pregunta.respuestaCorrecta;
    let respuestaMostrar = [];

    for (let i = 0; i < respuestaIncorecta.length; i++) {
        let respuesta = respuestaIncorecta[i];
        respuestaMostrar.push(respuesta);

    }

    respuestaMostrar.push(respuestaCorrecta);

    while (respuestaMostrar.length > 0) {
        let indice = Math.floor(Math.random() * respuestaMostrar.length);
        let respuesta = respuestaMostrar[indice];
        filaRespuesta(respuesta, respuesta === respuestaCorrecta);
        respuestaMostrar.splice(indice, 1);
    }
    let botonesRespuestas = document.getElementsByClassName("btnRespuesta");
    for (let i = 0; i < botonesRespuestas.length; i++) {
        let botonRespuesta = botonesRespuestas[i];
        botonRespuesta.addEventListener("click", verificarRespuesta);
        botonRespuesta.style.background = pregunta.tema.color;
    }
    

}

//funcion encargada de mostrar respuestas
function filaRespuesta(respuesta, esCorrecta) {
    let boton = document.createElement("input");
    let divBotones = document.getElementById("botonesDeRespuestas");
    boton.setAttribute("type", "button");
    boton.setAttribute("value", respuesta);
    boton.setAttribute("class", "btnRespuesta");
    boton.setAttribute("data-correcta", esCorrecta);
    divBotones.appendChild(boton);
    
}

//funcioin que verifica si la respuesta elegida es correcta o incorrecta
function verificarRespuesta() {
    let esCorrecta = this.getAttribute("data-correcta") === "true";

    if (esCorrecta) {
        this.style.background = "green";
        let botonesRespuestas = document.getElementsByClassName("btnRespuesta");
        let sonido1 = document.getElementById("sonido1");
        sonido1.play();
        for (let i = 0; i < botonesRespuestas.length; i++) {
            let botonRespuesta = botonesRespuestas[i];
            botonRespuesta.disabled = true;

        }
        puntajeAcumulado();
    } else {
        this.style.background = "red";
        this.disabled = true;
        let sonido2 = document.getElementById("sonido2");
        sonido2.play();
        puntajeAcumuladoMenos();
    }
   
}
let cantidadMax = 0;
let cantidad = 0;

//funcion que suma al puntaje
function puntajeAcumulado() {
    let puntaje = document.getElementById("puntajeAcumulado");
    puntaje.innerHTML = "";
    cantidad = cantidad + 10;
    puntaje.textContent = "Puntaje acumulado en esta Partida: " + cantidad;
}

//funcion que resta al puntaje
function puntajeAcumuladoMenos() {
    let puntaje = document.getElementById("puntajeAcumulado");
    puntaje.innerHTML = "";
    cantidad = cantidad - 1;
    puntaje.textContent = "Puntaje acumulado en esta Partida: " + cantidad;
}

//funcion que actualiza el puntaje maximo
function puntajeMax() {
    let puntajeMaximo = document.getElementById("MaximoPuntaje");
    puntajeMaximo.innerHTML = "";
    if (cantidad > cantidadMax) {
        cantidadMax = cantidad;
    }
    puntajeMaximo.textContent = "Máximo puntaje obtenido por un jugador: " + cantidadMax;

}

//funcion que da pista
function ayuda() {
    let botonesRespuestas = document.getElementsByClassName("btnRespuesta");
    for (let i = 0; i < botonesRespuestas.length; i++) {
        let botonRespuesta = botonesRespuestas[i];
        let respuesta = botonRespuesta.getAttribute("data-correcta");
        if (respuesta === "true") {
            alert(botonRespuesta.value.charAt(0));
            document.getElementById("botonAyuda").disabled = true;
        }
    }
}

//funcion que da otra pregunta si es que queda alguna
function siguientePregunta() {
    if (listaPreguntas.length > 0) {
        let siguientePregunta = proximaPregunta();
        let indice = listaPreguntas.indexOf(siguientePregunta);
        listaPreguntas.splice(indice, 1);
        mostrarPregunta(siguientePregunta);
        document.getElementById("botonAyuda").disabled = false;
    }

    if (listaPreguntas.length === 0) {
        document.getElementById("botonSgpregunta").disabled = true;
    }
}

//funcion que finaliza el juego
function terminar() {
    document.getElementById("temaAelegir").disabled = false;
    document.getElementById("NivelJuego").disabled = false;
    document.getElementById("boton3").disabled = false;
    document.getElementById("botonSgpregunta").disabled = false;
    document.getElementById("botonAyuda").disabled = false;
    alert("Puntaje acumulado es: " + cantidad);
    puntajeMax();
    cantidad = 0;
    actualizarPuntaje();
    Contenido3();
}

//funcion que actualiza puntaje
function actualizarPuntaje() {
    let puntaje = document.getElementById("puntajeAcumulado");
    puntaje.textContent = "Puntaje acumulado en esta Partida: " + cantidad;
}

