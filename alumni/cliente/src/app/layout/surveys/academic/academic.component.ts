import {Component, OnInit} from '@angular/core';
import {PreguntasService} from '../../../services/preguntas.service';
import {EncuestaPreguntaService} from '../../../services/encuesta-pregunta.service';
import {Respuesta} from '../../../model/Respuesta';
import {EncuestaPregunta} from '../../../model/EncuestaPregunta';
import {RespuestasService} from '../../../services/respuestas.service';
import {Persona} from '../../../model/Persona';
import {Encuesta} from '../../../model/Encuesta';
import {Preguntas} from '../../../model/Preguntas';
import {PersonaService} from '../../../services/persona.service';

@Component({
  selector: 'app-academic',
  templateUrl: './academic.component.html',
  styleUrls: ['./academic.component.css']
})
export class AcademicComponent implements OnInit {

  encuesta: any = [];
  preguntaRes: any = {};
  objeto1: any = {};
  objeto2: any = {};
  preguntas: any = [];
  tableResponse: any = [];
  tableResponse1: any = [];
  tableResponse2: any = [];
  respuesta: Respuesta = new Respuesta();
  poseeTitulo: any;
  nombrePos: any;
  pregunta: Preguntas = new Preguntas();
  enc: Encuesta = new Encuesta();
  persona: Persona = new Persona();
  universidad: any;
  paisEstudio: any;
  encuestaPregunta: EncuestaPregunta = new EncuestaPregunta();
  array: any = [];
  id: any;
  cedula: any;
  idPregunta: any = 17;
  idPregunta1: any = 21;
  nuevaFormacion: any;
  tipoFormacion: any;
  titulosObtenidos: any = [];
  tipoUni: any;
  nombreUni: any;

  constructor(private encuestaServices: EncuestaPreguntaService, private preguntasServices: PreguntasService, private respuestaServices: RespuestasService,
              private personaServices: PersonaService) {
    this.id = sessionStorage.getItem('id');
    this.cedula = sessionStorage.getItem('cedula');
    this.getAcademicBanner();
    this.getAcademicEncuesta();
    this.getPreguntas();
    this.llenarTabla();
  }

  ngOnInit() {
  }

  getAcademicBanner() {
    this.personaServices.obtenerCarrera(this.cedula).subscribe(r => {
      this.titulosObtenidos = r;
    });
  }

  getAcademicEncuesta() {
    this.encuestaServices.getEncuestaPregunta().subscribe(r => {
      let array: any = [];
      array = r;
      for (let i = 0; i < array.length; i++) {
        if (array[i].encuesta.nombre == 'INFORMACION ACADEMICA' && array[i].administrador == 'EDITABLE') {
          this.encuesta.push(array[i]);
        }
        if (array[i].respuesta != null) {
          if (array[i].respuesta.respuesta == 'SI' && array[i].respuesta.persona.id == this.id) {
            if (array[i].preguntas.id == 17) {
              this.poseeTitulo = 'SI';
            }
            if (array[i].preguntas.id == 21) {
              this.nuevaFormacion = 'SI';
            }
          }
        }
      }
    });
  }

  getPreguntas() {

    this.preguntasServices.getPregunta().subscribe(r => {
      this.preguntas = r;
    });
  }

  guardar(poseeTitulo, nombrePos, universidad, paisEstudio) {
    this.array.push(poseeTitulo);
    this.array.push(nombrePos);
    this.array.push(universidad);
    this.array.push(paisEstudio);
    this.persona.id = this.id;
    this.enc.id = this.encuesta[0].encuesta.id;
    for (let i = 0; i < this.array.length; i++) {
      if (i == 0) {
        this.respuesta.descripcion = '';
      }
      if (i == 1) {
        this.respuesta.descripcion = 'NOMBREPOSGRADO';
      }
      if (i == 2) {
        this.respuesta.descripcion = 'UNIVERSIDAD';
      }
      if (i == 3) {
        this.respuesta.descripcion = 'PAIS';
      }
      this.respuesta.respuesta = this.array[i];
      this.respuesta.persona = this.persona;
      this.respuestaServices.saveRespuestas(this.respuesta).subscribe(r => {
        let objeto: any;
        objeto = r;
        this.encuestaPregunta.encuesta = this.enc;

        this.pregunta.id = this.idPregunta + i;
        this.encuestaPregunta.preguntas = this.pregunta;
        this.encuestaPregunta.respuesta = objeto;
        this.encuestaPregunta.estado = 'LLENO';
        this.encuestaServices.guardarEncuestaPregunta(this.encuestaPregunta).subscribe(res => {

        });
      });
    }
    this.array = [];
    this.objeto1.nombrePos = nombrePos;
    this.objeto1.universidad = universidad;
    this.objeto1.pais = paisEstudio;
    this.tableResponse1.push(this.objeto1);
    this.nombrePos = '';
    this.universidad = '';
    this.paisEstudio = '';
  }

  guardar2(nuevaFormacion, tipoFormacion, tipoUni, nombreUni) {
    this.array.push(nuevaFormacion);
    this.array.push(tipoFormacion);
    this.array.push(tipoUni);
    this.array.push(nombreUni);
    this.persona.id = this.id;
    this.enc.id = this.encuesta[0].encuesta.id;
    for (let i = 0; i < this.array.length; i++) {
      if (i == 0) {
        this.respuesta.descripcion = '';
      }
      if (i == 1) {
        this.respuesta.descripcion = 'TIPOFORMACION';
      }
      if (i == 2) {
        this.respuesta.descripcion = 'TIPOUNIVERSIDAD';
      }
      if (i == 3) {
        this.respuesta.descripcion = 'NOMBREUNIVERSIDAD';
      }
      this.respuesta.respuesta = this.array[i];
      this.respuesta.persona = this.persona;
      this.respuestaServices.saveRespuestas(this.respuesta).subscribe(r => {
        let objeto: any;
        objeto = r;
        this.encuestaPregunta.encuesta = this.enc;
        this.pregunta.id = this.idPregunta1 + i;
        this.encuestaPregunta.preguntas = this.pregunta;
        this.encuestaPregunta.respuesta = objeto;
        this.encuestaPregunta.estado = 'LLENO';
        console.log(this.encuestaPregunta)
        this.encuestaServices.guardarEncuestaPregunta(this.encuestaPregunta).subscribe(res => {

        });
      });
    }
    this.array = [];
    this.objeto2.tipoFormacion = tipoFormacion;
    this.objeto2.tipoUni = tipoUni;
    this.objeto2.nombreUni = nombreUni;
    this.tableResponse2.push(this.objeto2);
    this.tipoFormacion = '';
    this.tipoUni = '';
    this.nombreUni = '';
  }

  llenarTabla() {
    this.encuestaServices.getEncuestaPregunta().subscribe(r => {
      let array: any = [];
      array = r;
      for (let i = 0; i < array.length; i++) {
        if (array[i].encuesta.nombre == 'INFORMACION ACADEMICA' && array[i].estado == 'LLENO') {
          this.tableResponse.push(array[i]);
          console.log(array[i]);
        }
      }
      this.separarRespuestas();
    });
  }

  separarRespuestas() {
    for (let j = 0; j < this.tableResponse.length; j++) {
      if (this.tableResponse[j].respuesta.descripcion == 'NOMBREPOSGRADO') {
        this.objeto1.nombrePos = this.tableResponse[j].respuesta.respuesta;
      }
      if (this.tableResponse[j].respuesta.descripcion == 'UNIVERSIDAD') {
        this.objeto1.universidad = this.tableResponse[j].respuesta.respuesta;
      }
      if (this.tableResponse[j].respuesta.descripcion == 'PAIS') {
        this.objeto1.pais = this.tableResponse[j].respuesta.respuesta;
      }
      if (this.tableResponse[j].respuesta.descripcion == 'TIPOFORMACION') {
        this.objeto2.tipoFormacion = this.tableResponse[j].respuesta.respuesta;
      }
      if (this.tableResponse[j].respuesta.descripcion == 'TIPOUNIVERSIDAD') {
        this.objeto2.tipoUni = this.tableResponse[j].respuesta.respuesta;
      }
      if (this.tableResponse[j].respuesta.descripcion == 'NOMBREUNIVERSIDAD') {
        this.objeto2.nombreUni = this.tableResponse[j].respuesta.respuesta;
      }
      if (this.objeto1.nombrePos != undefined && this.objeto1.universidad != undefined && this.objeto1.pais != undefined) {
        this.tableResponse1.push(this.objeto1);
        this.objeto1 = {};
      }
      if (this.objeto2.tipoFormacion != undefined && this.objeto2.tipoUni != undefined && this.objeto2.nombreUni != undefined) {
        this.tableResponse2.push(this.objeto2);
        this.objeto2 = {};
      }
    }
  }

}
