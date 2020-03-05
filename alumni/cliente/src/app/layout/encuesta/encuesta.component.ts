import {Component, OnInit} from '@angular/core';
import {EncuestaPreguntaService} from '../../services/encuesta-pregunta.service';
import {Respuesta} from '../../model/Respuesta';
import {RespuestasService} from '../../services/respuestas.service';
import {PreguntasService} from '../../services/preguntas.service';
import {Persona} from '../../model/Persona';
import {EncuestaPregunta} from '../../model/EncuestaPregunta';
import {Preguntas} from '../../model/Preguntas';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-encuesta',
  templateUrl: './encuesta.component.html',
  styleUrls: ['./encuesta.component.css']
})

export class EncuestaComponent implements OnInit {

  encuesta: any = [];
  logro: any;
  nivel: any;
  respuesta: Respuesta = new Respuesta();
  persona: Persona = new Persona();
  encuestaPreguntas: EncuestaPregunta = new EncuestaPregunta();
  pregunta: Preguntas = new Preguntas();
  respuestas: any = [];
  idUser = sessionStorage.getItem('id');
  id: any;
  contador: any = 0;


  constructor(private encuestaPreguntaServices: EncuestaPreguntaService, private respuestaServices: RespuestasService, private route: Router, private toast: ToastrService) {
    this.getGenericEncuesta();
  }

  ngOnInit() {
  }


  getGenericEncuesta() {
    this.encuestaPreguntaServices.getEncuestaPregunta().subscribe(r => {
      let array: any = [];
      array = r;
      for (let i = 0; i < array.length; i++) {
        if (array[i].encuesta.nombre == 'CAPACITACION GENERICA') {
          if (array[i].respuesta == null) {
            this.encuesta.push(array[i]);
            if (this.contador == 17) {
              this.encuesta = [];
            }
            this.contador++;
          }
          if (this.contador > 16) {
            if (array[i].estado == 'LLENO') {
              // tslint:disable-next-line:radix
              if (array[i].respuesta.persona.id == parseInt(this.idUser)) {
                this.encuesta.push(array[i]);
              }
            }
          }
        }
      }
    });
  }

  agregar(data, idPregunta, descripcion) {
    // Agregamos el elemento
    const objeto: any = {};
    objeto.respuesta = data;
    objeto.idPregunta = idPregunta;
    objeto.descripcion = descripcion;
    this.respuestas.push(objeto);
  }


  validar(data, idPregunta, descripcion) {
    if (this.respuestas.length == 0) {
      this.agregar(data, idPregunta, descripcion);
    } else {
      const resultado = this.respuestas.find(idPreg => idPreg.idPregunta == idPregunta && idPreg.descripcion == descripcion);
      if (resultado == undefined) {
        this.agregar(data, idPregunta, descripcion);
      } else {
        this.respuestas = this.respuestas.filter(s => s !== resultado);
        const objeto: any = {};
        objeto.respuesta = data;
        objeto.idPregunta = idPregunta;
        objeto.descripcion = descripcion;
        this.respuestas.push(objeto);
      }
    }
  }

  guardar() {
    for (let i = 0; i < this.respuestas.length; i++) {
      this.persona.id = parseInt(this.idUser);
      this.respuesta.respuesta = this.respuestas[i].respuesta;
      this.respuesta.descripcion = this.respuestas[i].descripcion;
      this.respuesta.persona = this.persona;
      this.respuestaServices.saveRespuestas(this.respuesta).subscribe(r => {
        let object: any = {};
        object = r;
        this.pregunta.id = this.respuestas[i].idPregunta;
        this.encuestaPreguntas.respuesta = object;
        this.encuestaPreguntas.preguntas = this.pregunta;
        this.encuestaPreguntas.estado = 'LLENO';
        this.encuestaPreguntas.encuesta = this.encuesta[0].encuesta;
        console.log(this.encuestaPreguntas);
        this.encuestaPreguntaServices.guardarEncuestaPregunta(this.encuestaPreguntas).subscribe(res => {
          this.toast.success('Se han guardado sus respuestas', 'Guardado con exito');
        });
      });
    }
    this.route.navigate(['/home']);
  }

}
