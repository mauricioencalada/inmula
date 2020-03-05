import {Component, OnInit} from '@angular/core';
import {PreguntasService} from '../../../services/preguntas.service';
import {EncuestaPreguntaService} from '../../../services/encuesta-pregunta.service';
import {RespuestasService} from '../../../services/respuestas.service';
import {Respuesta} from '../../../model/Respuesta';
import {Persona} from '../../../model/Persona';
import {EncuestaPregunta} from '../../../model/EncuestaPregunta';
import {Preguntas} from '../../../model/Preguntas';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';

@Component({
  selector: 'app-employability',
  templateUrl: './employability.component.html',
  styleUrls: ['./employability.component.css']
})
export class EmployabilityComponent implements OnInit {

  validador: any = false;
  idUser = sessionStorage.getItem('id');
  preguntas: any = [];
  encuestaPreguntas: any = [];
  encuestaArray: any = [];
  contador: any = 0;
  preg1: any = '';
  preg2: any = '';
  preg3: any = '';
  preg4: any = '';
  preg5: any = '';
  preg6: any = '';
  res1: any = '';
  res2: any = '';
  res3: any = '';
  res4: any = '';
  res5: any = '';
  res6: any = '';
  res7: any = '';
  res8: any = '';
  res9: any = '';
  res10: any = '';
  res11: any = '';
  res12: any = '';
  res13: any = '';
  res14: any = '';
  res15: any = '';
  res16: any = '';
  res17: any = '';
  res18: any = '';
  res19: any = '';
  res20: any = '';
  res21: any = '';
  res22: any = '';
  res23: any = '';
  res24: any = '';
  res25: any = '';
  res26: any = '';
  res27: any = '';
  res28: any = '';
  res29: any = '';
  res30: any = '';
  res31: any = '';
  res32: any = '';
  resM1: any = '';
  resM2: any = '';
  resM3: any = '';
  resM4: any = '';
  id1: any = 25;
  id2: any = 25;
  id3: any = 26;
  id4: any = 27;
  id5: any = 32;
  id6: any = 33;
  id7: any = 33;
  id8: any = 34;
  id9: any = 673;
  id10: any = 674;
  id11: any = 35;
  id12: any = 36;
  id13: any = 37;
  id14: any = 38;
  id15: any = 39;
  id16: any = 675;
  id17: any = 41;
  id18: any = 43;
  id19: any = 44;
  id20: any = 45;
  id21: any = 42;
  id22: any = 46;
  id23: any = 47;
  id24: any = 48;
  id25: any = 49;
  id26: any = 50;
  id27: any = 51;
  id28: any = 52;
  id29: any = 53;
  id30: any = 54;
  id31: any = 55;
  id32: any = 57;
  idM1: any = 28;
  idM2: any = 29;
  idM3: any = 30;
  idM4: any = 31;
  ocularCarrera1 = false;
  ocularCarrera2 = false;
  ocularCarrera3 = false;
  ocularCarrera4 = false;
  ocularCarrera5 = false;
  ocularCarrera6 = false;
  ocularCarrera7 = false;
  ocularCarrera8 = false;
  ocularCarrera9 = false;
  ocularCarrera10 = false;
  ocularCarrera11 = false;
  ocularCarrera12 = false;
  ocularCarrera13 = false;
  ocularCarrera14 = false;
  ocularCarrera15 = false;
  ocularCarrera16 = false;
  ocularCarrera17 = false;
  ocularCarrera18 = false;
  ocularCarrera19 = false;
  ocularCarrera20 = false;
  ocularCarrera21 = false;
  ocularCarrera22 = false;
  ocularCarrera23 = false;
  ocularCarrera24 = false;
  ocularCarrera25 = false;
  ocularCarrera26 = false;
  ocularCarrera27 = false;
  ocularCarrera28 = false;
  ocularCarrera29 = false;
  ocularCarrera30 = false;
  ocularCarrera31 = false;
  ocularCarreraM1 = false;
  ocularCarreraM2 = false;
  ocularCarreraM3 = false;
  ocularCarreraM4 = false;
  respuesta: Respuesta = new Respuesta();
  persona: Persona = new Persona();
  encuestaPregunta: EncuestaPregunta = new EncuestaPregunta();
  pregunta: Preguntas = new Preguntas();
  tableObject: any = {};
  tableArray: any = [];
  tableArray1: any = [];



  constructor(private preguntaServices: PreguntasService, private encuestaServices: EncuestaPreguntaService, private respuestaServices: RespuestasService, private toast: ToastrService, private route: Router) {
    this.getData();
    this.llenarTabla();
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
        if (array[i].encuesta.nombre == 'EMPLEABILIDAD') {
          if (array[i].respuesta == null) {
            this.encuestaPreguntas.push(array[i]);
            this.validador = false;

          }
          if (i > 34) {
            if (this.contador == 25) {
              this.encuestaPreguntas = [];
            }
            this.contador++;
            if (array[i].estado == 'LLENO' && array[i].administrador != 'EDITABLE') {
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
        if (array[i].nombre == 'EMPLEABILIDAD') {
          this.encuestaArray.push(array[i]);
        }
      }
    });

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
        if (this.encuestaPreguntas[i].preguntas.id == 27 && this.encuestaPreguntas[i].respuesta.respuesta > 1) {
          this.res4 = this.encuestaPreguntas[i].respuesta.respuesta;
        }
        if (this.encuestaPreguntas[i].preguntas.id == 32 && this.encuestaPreguntas[i].respuesta.respuesta == 'SI') {
          this.res5 = this.encuestaPreguntas[i].respuesta.respuesta;
        }
        if (this.encuestaPreguntas[i].preguntas.id == 673 && this.encuestaPreguntas[i].respuesta.respuesta == 'OTRO') {
          this.res9 = this.encuestaPreguntas[i].respuesta.respuesta;
        }
        if (this.encuestaPreguntas[i].preguntas.id == 39 && this.encuestaPreguntas[i].respuesta.respuesta == 'OTRO') {
          this.res15 = this.encuestaPreguntas[i].respuesta.respuesta;
        }
        if (this.encuestaPreguntas[i].preguntas.id == 47 && this.encuestaPreguntas[i].respuesta.respuesta == 'NO') {

          this.res23 = this.encuestaPreguntas[i].respuesta.respuesta;
        }
        if (this.encuestaPreguntas[i].preguntas.id == 50 && this.encuestaPreguntas[i].respuesta.respuesta == 'OTRO') {
          this.res26 = this.encuestaPreguntas[i].respuesta.respuesta;
        }
        if (this.encuestaPreguntas[i].preguntas.id == 52 && this.encuestaPreguntas[i].respuesta.respuesta == 'OTRO') {
          this.res28 = this.encuestaPreguntas[i].respuesta.respuesta;
        }
        if (this.encuestaPreguntas[i].preguntas.id == 54 && this.encuestaPreguntas[i].respuesta.respuesta == 'OTRO') {
          this.res30 = this.encuestaPreguntas[i].respuesta.respuesta;
        }
      }
    }
  }

  guardarModal() {
    const array: any = [];
    array.push({respuesta: this.resM1, idPregunta: this.idM1});
    array.push({respuesta: this.resM2, idPregunta: this.idM2});
    array.push({respuesta: this.resM3, idPregunta: this.idM3});
    array.push({respuesta: this.resM4, idPregunta: this.idM4});
    for (let i = 0; i < array.length; i++) {
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
        }, error => {
          console.log(error);
        });
      });
    }
    this.tableObject.nombreEmp = this.resM1;
    this.tableObject.cargo = this.resM2;
    this.tableObject.tiempo = this.resM3;
    this.tableObject.anio = this.resM4;
    this.tableArray1.push(this.tableObject);
  }

  guardarData() {
    const array: any = [];
    array.push({respuesta: this.res1, idPregunta: this.id1});
    array.push({respuesta: this.res2, idPregunta: this.id2});
    array.push({respuesta: this.res3, idPregunta: this.id3});
    array.push({respuesta: this.res4, idPregunta: this.id4});
    array.push({respuesta: this.resM1, idPregunta: this.idM1});
    array.push({respuesta: this.resM2, idPregunta: this.idM2});
    array.push({respuesta: this.resM3, idPregunta: this.idM3});
    array.push({respuesta: this.resM4, idPregunta: this.idM4});
    array.push({respuesta: this.res5, idPregunta: this.id5});
    array.push({respuesta: this.res6, idPregunta: this.id6});
    array.push({respuesta: this.res7, idPregunta: this.id7});
    array.push({respuesta: this.res8, idPregunta: this.id8});
    array.push({respuesta: this.res9, idPregunta: this.id9});
    array.push({respuesta: this.res10, idPregunta: this.id10});
    array.push({respuesta: this.res11, idPregunta: this.id11});
    array.push({respuesta: this.res12, idPregunta: this.id12});
    array.push({respuesta: this.res13, idPregunta: this.id13});
    array.push({respuesta: this.res14, idPregunta: this.id14});
    array.push({respuesta: this.res15, idPregunta: this.id15});
    array.push({respuesta: this.res16, idPregunta: this.id16});
    array.push({respuesta: this.res17, idPregunta: this.id17});
    array.push({respuesta: this.res18, idPregunta: this.id18});
    array.push({respuesta: this.res19, idPregunta: this.id19});
    array.push({respuesta: this.res20, idPregunta: this.id20});
    array.push({respuesta: this.res21, idPregunta: this.id21});
    array.push({respuesta: this.res22, idPregunta: this.id22});
    array.push({respuesta: this.res23, idPregunta: this.id23});
    array.push({respuesta: this.res24, idPregunta: this.id24});
    array.push({respuesta: this.res25, idPregunta: this.id25});
    array.push({respuesta: this.res26, idPregunta: this.id26});
    array.push({respuesta: this.res27, idPregunta: this.id27});
    array.push({respuesta: this.res28, idPregunta: this.id28});
    array.push({respuesta: this.res29, idPregunta: this.id29});
    array.push({respuesta: this.res30, idPregunta: this.id30});
    array.push({respuesta: this.res31, idPregunta: this.id31});
    array.push({respuesta: this.res32, idPregunta: this.id32});

    for (let i = 0; i < array.length; i++) {
      if (i == 0) {
        this.respuesta.descripcion = 'ANTES';
      }
      if (i == 1) {
        this.respuesta.descripcion = 'DESPUES';
      }
      if (i == 9) {
        this.respuesta.descripcion = 'ANIOS';
      }
      if (i == 10) {
        this.respuesta.descripcion = 'MESES';
      }
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
          this.route.navigate(['/web/infobo']);
        }, error => {
          console.log(error);
        });
      });
    }

  }

  actualizarData() {
    const array: any = [];
    array.push({respuesta: this.res1, idPregunta: this.id1});
    array.push({respuesta: this.res2, idPregunta: this.id2});
    array.push({respuesta: this.res3, idPregunta: this.id3});
    array.push({respuesta: this.res4, idPregunta: this.id4});
    array.push({respuesta: this.resM1, idPregunta: this.idM1});
    array.push({respuesta: this.resM2, idPregunta: this.idM2});
    array.push({respuesta: this.resM3, idPregunta: this.idM3});
    array.push({respuesta: this.resM4, idPregunta: this.idM4});
    array.push({respuesta: this.res5, idPregunta: this.id5});
    array.push({respuesta: this.res6, idPregunta: this.id6});
    array.push({respuesta: this.res7, idPregunta: this.id7});
    array.push({respuesta: this.res8, idPregunta: this.id8});
    array.push({respuesta: this.res9, idPregunta: this.id9});
    array.push({respuesta: this.res10, idPregunta: this.id10});
    array.push({respuesta: this.res11, idPregunta: this.id11});
    array.push({respuesta: this.res12, idPregunta: this.id12});
    array.push({respuesta: this.res13, idPregunta: this.id13});
    array.push({respuesta: this.res14, idPregunta: this.id14});
    array.push({respuesta: this.res15, idPregunta: this.id15});
    array.push({respuesta: this.res16, idPregunta: this.id16});
    array.push({respuesta: this.res17, idPregunta: this.id17});
    array.push({respuesta: this.res18, idPregunta: this.id18});
    array.push({respuesta: this.res19, idPregunta: this.id19});
    array.push({respuesta: this.res20, idPregunta: this.id20});
    array.push({respuesta: this.res21, idPregunta: this.id21});
    array.push({respuesta: this.res22, idPregunta: this.id22});
    array.push({respuesta: this.res23, idPregunta: this.id23});
    array.push({respuesta: this.res24, idPregunta: this.id24});
    array.push({respuesta: this.res25, idPregunta: this.id25});
    array.push({respuesta: this.res26, idPregunta: this.id26});
    array.push({respuesta: this.res27, idPregunta: this.id27});
    array.push({respuesta: this.res28, idPregunta: this.id28});
    array.push({respuesta: this.res29, idPregunta: this.id29});
    array.push({respuesta: this.res30, idPregunta: this.id30});
    array.push({respuesta: this.res31, idPregunta: this.id31});
    array.push({respuesta: this.res32, idPregunta: this.id32});

    if (array[0].respuesta == '' || array[0].respuesta == undefined && array[1].respuesta == '' || array[1].respuesta == undefined && array[2].respuesta == '' || array[2].respuesta == undefined && array[3].respuesta == '' || array[3].respuesta == undefined && array[4].respuesta == '' || array[4].respuesta == undefined
      && array[5].respuesta == '' || array[5].respuesta == undefined && array[6].respuesta == '' || array[6].respuesta == undefined && array[7].respuesta == '' || array[7].respuesta == undefined && array[8].respuesta == '' || array[8].respuesta == undefined && array[9].respuesta == '' || array[9].respuesta == undefined && array[10].respuesta == '' || array[10].respuesta == undefined
      && array[11].respuesta == '' || array[11].respuesta == undefined && array[12].respuesta == '' || array[12].respuesta == undefined && array[13].respuesta == '' || array[13].respuesta == undefined && array[14].respuesta == '' || array[14].respuesta == undefined && array[15].respuesta == '' || array[15].respuesta == undefined && array[16].respuesta == '' || array[16].respuesta == undefined
      && array[17].respuesta == '' || array[17].respuesta == undefined && array[18].respuesta == '' || array[18].respuesta == undefined && array[19].respuesta == '' || array[19].respuesta == undefined && array[20].respuesta == '' || array[20].respuesta == undefined && array[21].respuesta == '' || array[21].respuesta == undefined && array[22].respuesta == '' || array[22].respuesta == undefined
      && array[23].respuesta == '' || array[23].respuesta == undefined && array[24].respuesta == '' || array[24].respuesta == undefined && array[25].respuesta == '' || array[25].respuesta == undefined && array[26].respuesta == '' || array[26].respuesta == undefined && array[27].respuesta == '' || array[27].respuesta == undefined && array[28].respuesta == '' || array[28].respuesta == undefined && array[29].respuesta == '' || array[29].respuesta == undefined
      && array[30].respuesta == '' || array[30].respuesta == undefined && array[31].respuesta == '' || array[31].respuesta == undefined && array[32].respuesta == '' || array[32].respuesta == undefined && array[33].respuesta == '' || array[33].respuesta == undefined && array[34].respuesta == '' || array[34].respuesta == undefined && array[35].respuesta == '' || array[35].respuesta == undefined) {
      this.route.navigate(['/web/infobo']);
    }

    for (let i = 0; i < array.length; i++) {

      if (array[i].respuesta == '' || array[i].respuesta == undefined) {

      } else {
        this.respuesta.respuesta = array[i].respuesta;
        this.respuesta.id = array[i].idPregunta;
        this.respuestaServices.updateRespuesta(this.respuesta).subscribe(r => {
          this.toast.success('Se han guardado sus respuestas', 'Actualización con exito');
        });
      }
    }
    this.route.navigate(['/web/infobo']);
  }

  llenarTabla() {
    this.encuestaServices.getEncuestaPregunta().subscribe(r => {
      let array: any = [];
      array = r;
      for (let i = 0; i < array.length; i++) {
        if (array[i].encuesta.nombre == 'EMPLEABILIDAD' && array[i].estado == 'LLENO') {
          this.tableArray.push(array[i]);
        }
      }
      this.separarRespuestas();
    });
  }

  separarRespuestas() {
    for (let j = 0; j < this.tableArray.length; j++) {
      if (this.tableArray[j].preguntas.id == 28) {
        console.log(this.tableArray[j]);
        this.tableObject.nombreEmp = this.tableArray[j].respuesta.respuesta;
      }
      if (this.tableArray[j].preguntas.id == 29) {
        console.log(this.tableArray[j]);
        this.tableObject.cargo = this.tableArray[j].respuesta.respuesta;
      }
      if (this.tableArray[j].preguntas.id == 30) {
        console.log(this.tableArray[j]);
        this.tableObject.tiempo = this.tableArray[j].respuesta.respuesta;
      }
      if (this.tableArray[j].preguntas.id == 31) {
        console.log(this.tableArray[j]);
        this.tableObject.anio = this.tableArray[j].respuesta.respuesta;
      }
      if (this.tableObject.nombreEmp != undefined && this.tableObject.cargo != undefined && this.tableObject.tiempo != undefined && this.tableObject.anio != undefined) {
        this.tableArray1.push(this.tableObject);
        this.tableObject = {};
      }

    }
  }

}
