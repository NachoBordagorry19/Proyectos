//Juan Ignacio Bordagorry (328149)
//Joaquín Gonzales (322133)


class Sistema {
    constructor() {
        this.listaPreguntas = [];
        this.listaTemas = [];
    }

    getTema(nombre) {
        for (let i = 0; i < this.listaTemas.length; i++) {
            if (this.listaTemas[i].nombre == nombre) {
                return this.listaTemas[i];
            }
        }
    }

    agregarTema(A) {
        let ok = true;
        for (let i = 0; i < this.listaTemas.length && ok == true; i++) {
            if (A.nombre === this.listaTemas[i].nombre) {
                ok = false;
            }
        }
        if (ok == true) {
            this.listaTemas.push(A);
        }
        return ok;
    }

    agregarPregunta(A) {
        this.listaPreguntas.push(A);
    }

    sumarNumero() {
        return this.listaTemas.length;
    }

    sumarNumeroPregunta() {
        return this.listaPreguntas.length;
    }

    temaConPreguntas(A) {
        let ok = false
        for (let i = 0; i < this.listaPreguntas.length && ok == false; i++) {
            if (this.listaPreguntas[i].tema.nombre == A.nombre) {
                ok = true;
            }
        }
        return ok;
    }
    
    listaPreguntasTema(nombreTema, nivel) {
        let lista = [];
        for (let i = 0; i < this.listaPreguntas.length; i++) {
            let pregunta = this.listaPreguntas[i]
            if (nivel == pregunta.nivel && nombreTema == pregunta.tema.nombre) {

                lista.push(pregunta);
            }
        }
        return lista;
    }

}

class Tema {
    constructor(nombre, descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.color = "";
    }

    toString() {
        return this.nombre
    }


}

class Pregunta {
    constructor(tema, nivel, textoPregunta, respuestaCorrecta, respuestaIncorecta) {
        this.tema = tema;
        this.nivel = nivel;
        this.textoPregunta = textoPregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestaIncorecta = respuestaIncorecta;
    }

    
}


