import {Component, OnInit, AfterViewInit} from '@angular/core';
import {PreguntasService} from '../../../services/preguntas.service';
import {EncuestaPreguntaService} from '../../../services/encuesta-pregunta.service';
import {RespuestasService} from '../../../services/respuestas.service';
import {Respuesta} from '../../../model/Respuesta';
import {Persona} from '../../../model/Persona';
import {Preguntas} from '../../../model/Preguntas';
import {EncuestaPregunta} from '../../../model/EncuestaPregunta';
import * as $ from 'jquery';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
/*import * as jsPDF from 'jspdf';
import { PdfViewerModule } from 'ng2-pdf-viewer';*/



@Component({
  selector: 'app-information-bonding',
  templateUrl: './information-bonding.component.html',
  styleUrls: ['./information-bonding.component.css']
})
export class InformationBondingComponent implements OnInit {
  doc: any;
  select1: any;
  select2: any;
  idUser = sessionStorage.getItem('id');
  preguntas: any = [];
  encuestaPreguntas: any = [];
  encuestaArray: any = [];
  carrera1: any = '';
  carrera2: any = '';
  carrera3: any = '';
  programa1: any = '';
  programa2: any = '';
  programa3: any = '';
  responseOtro: any = '';
  id1: any = 58;
  id2: any = 61;
  id3: any = 59;
  id4: any = 62;
  id5: any = 60;
  id6: any = 63;
  id7: any = 64;
  id8: any;
  id9: any = 66;
  ocularCarrera1 = false;
  ocularCarrera2 = false;
  ocularCarrera3 = false;
  ocularCarrera4 = false;
  ocularCarrera5 = false;
  ocularCarrera6 = false;
  ocularCarrera7 = false;
  ocularCarrera8 = false;
  ocularCarrera9 = false;
  validador: any = false;
  contador: any = 0;
  respuesta: Respuesta = new Respuesta();
  persona: Persona = new Persona();
  encuestaPregunta: EncuestaPregunta = new EncuestaPregunta();
  pregunta: Preguntas = new Preguntas();
  respuestaOtro: any;
  ImageConstants = 'assets/img/logo3.png';

  constructor(private preguntaServices: PreguntasService, private encuestaServices: EncuestaPreguntaService, private respuestaServices: RespuestasService,
              private route: Router, private toast: ToastrService) {
    this.getData();

  }

  ngOnInit() {

  }


  getData() {
    this.preguntaServices.getPregunta().subscribe(r => {
      this.preguntas = r;
    });

    this.encuestaServices.getEncuestaPregunta().subscribe(r => {
      let array: any = [];
      array = r;
      for (let i = 0; i < array.length; i++) {
        if (array[i].encuesta.nombre == 'INFORMACIÓN COMPLEMENTARIA' || array[i].encuesta.nombre == 'VINCULACIÓN CON LA UNIVERSIDAD') {
          if (array[i].respuesta == null) {
            this.encuestaPreguntas.push(array[i]);
            this.validador = false;
            if (this.contador == 9) {
              this.encuestaPreguntas = [];
            }
            this.contador++;
          }
          if (i > 66) {
            if (array[i].estado == 'LLENO') {
              // tslint:disable-next-line:radix
              if (array[i].respuesta.persona.id == parseInt(this.idUser)) {
                this.encuestaPreguntas.push(array[i]);
                this.validador = true;
              }
            }
          }
        }
      }
      this.prueba();
    });


    this.encuestaServices.getEncuesta().subscribe(r => {
      let array: any = [];
      array = r;
      for (let i = 0; i < array.length; i++) {
        if (array[i].nombre == 'INFORMACIÓN COMPLEMENTARIA') {
          this.encuestaArray.push(array[i]);
        }
      }
    });

  }

  guardarData() {
    this.id8 = this.id7 + 1;
    const array: any = [];
    array.push({respuesta: this.carrera1, idPregunta: this.id1});
    array.push({respuesta: this.carrera2, idPregunta: this.id3});
    array.push({respuesta: this.carrera3, idPregunta: this.id5});
    array.push({respuesta: this.programa1, idPregunta: this.id2});
    array.push({respuesta: this.programa2, idPregunta: this.id4});
    array.push({respuesta: this.programa3, idPregunta: this.id6});
    array.push({respuesta: this.select1, idPregunta: this.id7});
    array.push({respuesta: this.responseOtro, idPregunta: this.id8});
    array.push({respuesta: this.select2, idPregunta: this.id9});
    for (let i = 0; i < array.length; i++) {
      // tslint:disable-next-line:radix
      this.persona.id = parseInt(this.idUser);
      this.respuesta.respuesta = array[i].respuesta;
      this.respuesta.persona = this.persona;
      this.respuestaServices.saveRespuestas(this.respuesta).subscribe(r => {
        let objeto: any;
        objeto = r;
        this.encuestaPregunta.encuesta = this.encuestaArray[0];
        this.encuestaPregunta.fechaRespuesta = Date.now();
        this.pregunta.id = array[i].idPregunta;
        this.encuestaPregunta.preguntas = this.pregunta;
        this.encuestaPregunta.respuesta = objeto;
        this.encuestaPregunta.estado = 'LLENO';
        this.encuestaServices.guardarEncuestaPregunta(this.encuestaPregunta).subscribe(res => {
          this.toast.success('Se han guardado sus respuestas', 'Guardado con exito');
          this.route.navigate(['/web/encuesta']);
        });
      });
    }

  }

  verificar(i) {
    if (i == 0) {
      return true;
    }
    return false;
  }

  prueba() {
    for (let i = 0; i < this.encuestaPreguntas.length; i++) {
      if (this.encuestaPreguntas[i].respuesta != null) {
        if (this.encuestaPreguntas[i].preguntas.id == 64 && this.encuestaPreguntas[i].respuesta.respuesta == 'OTRO') {
          this.respuestaOtro = 'OTRO';
        }
      }
    }
  }

  actualizarData() {
    const array: any = [];
    array.push({respuesta: this.carrera1, idRespuesta: this.id1});
    array.push({respuesta: this.carrera2, idRespuesta: this.id3});
    array.push({respuesta: this.carrera3, idRespuesta: this.id5});
    array.push({respuesta: this.programa1, idRespuesta: this.id2});
    array.push({respuesta: this.programa2, idRespuesta: this.id4});
    array.push({respuesta: this.programa3, idRespuesta: this.id6});
    array.push({respuesta: this.select1, idRespuesta: this.id7});
    array.push({respuesta: this.responseOtro, idRespuesta: this.id8});
    array.push({respuesta: this.select2, idRespuesta: this.id9});
    // tslint:disable-next-line:max-line-length
    if (array[0].respuesta == '' || array[0].respuesta == undefined && array[1].respuesta == '' || array[1].respuesta == undefined && array[2].respuesta == '' || array[2].respuesta == undefined && array[3].respuesta == '' || array[3].respuesta == undefined && array[4].respuesta == '' || array[4].respuesta == undefined
      && array[5].respuesta == '' || array[5].respuesta == undefined && array[6].respuesta == '' || array[6].respuesta == undefined && array[7].respuesta == '' || array[7].respuesta == undefined && array[8].respuesta == '' || array[8].respuesta == undefined) {
      this.route.navigate(['/web/encuesta']);
    }

    for (let i = 0; i < array.length; i++) {
      if (array[i].respuesta == '' || array[i].respuesta == undefined) {

      } else {
        this.respuesta.respuesta = array[i].respuesta;
        this.respuesta.id = array[i].idRespuesta;
        this.respuestaServices.updateRespuesta(this.respuesta).subscribe(r => {
          this.toast.success('Se han guardado sus respuestas', 'Guardado con exito');
        });
      }
    }
    this.route.navigate(['/home']);
  }

 /* PdfViewer() {

      const rojo = '#ef0606';
      const verde = '#27cf05';
      const lian = '#0D8DD7';
      const cian = '#836B6B';
      // tslint:disable-next-line: prefer-const
      let doc = new jsPDF({
        orientation: 'p',
        unit: 'mm',
        format: 'a4'
      });
      doc.setFontSize(16);
      doc.setFillColor(lian);
      doc.setDrawColor(cian);
      doc.rect(10, 10, 190, 275, 'DF');
      doc.setTextColor(rojo);
      doc.text('hola Pdf ya puedo crear documentos', 25, 25);
      doc.setTextColor(verde);
      doc.text('este lo q se va a imprimir', 25, 30);
      // tslint:disable-next-line: max-line-length
      doc.text('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 25, 35, { maxWidth: 150, maxHeigth: 200 });
      doc.text('este lo q se va a imprimir', 25, 150, { maxWidth: 150, aling: 'justify' });
      doc.text('este lo q se va a imprimir', 25, 160, { maxWidth: 150, angle: '45' });
      doc.addPage();
      doc.addPage('a4', 'l');
      doc.addPage('a2', ' p ');

      doc.setPage(3);
      doc.text('estamos en la 3era hoja', 10, 25);

      const imagen = new Image();
      imagen.src = 'assets/img/logo3.png';
      doc.addImage(imagen, 'PNG', 10, 50, 100, 100)
    doc.fromHTML($('#case-note-exported-content').get(0), 20, 20, {'width': 180});
    doc.output('dataurlnewwindow');
  }
  Print() {
    this.doc.autoPrint();
  }
  guardarpdf() {
    this.doc.save('ALUMNIESTUDIANTE' + '.pdf');
  }*/


}
