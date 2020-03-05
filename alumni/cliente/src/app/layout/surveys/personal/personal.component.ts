import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {PersonaService} from '../../../services/persona.service';
import {PersonaBanner} from '../../../model/PersonaBanner';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-personal',
  templateUrl: './personal.component.html',
  styleUrls: ['./personal.component.css']
})
export class PersonalComponent implements OnInit {
  id: any = sessionStorage.getItem('cedula');
  ecivil: any = [];
  paises: any = [];
  provincias: any = [];
  cantones: any = [];
  parroquias: any = [];
  array: any;
  personaBanner: any;
  x: any;

  constructor(private personaServices: PersonaService) {
    this.personaBanner = new PersonaBanner();
    this.personaServices.obtenerPais().subscribe(res => {
      this.paises = res;
    });
    this.obtenerPersona();
  }

  ngOnInit() {
    this.GETALL();
  }

  obtenerPersona() {
    this.personaServices.obtenerPersona(this.id).subscribe(r => {
      this.personaBanner = r[0];
      let array1 = this.personaBanner.nombres.split(', ');
      this.personaBanner.apellido = array1[0];
      this.personaBanner.nombre = array1[1];
      let array2 = this.personaBanner.pais_RES.split('-');
      this.personaBanner.cod_pais = array2[0];
      let array3 = this.personaBanner.provincia.split('-');
      this.personaBanner.cod_prov = array3[0];
      let cantonA = this.personaBanner.canton.split('-');
      this.personaBanner.cod_can = cantonA[0];
      this.personaServices.obtenerEstadoCivil().subscribe(re => {
        this.ecivil = re;
      });

      this.personaServices.obtenerProvincia().subscribe(resp => {
        this.provincias = resp;
      });
      this.obtenerDomicilio();
    });
  }

  obtenerDomicilio() {
    this.personaServices.obtenerCanton(this.personaBanner.cod_prov).subscribe(r => {
      this.cantones = r;
    });
    this.personaServices.obtenerParroquia(this.personaBanner.cod_can, this.personaBanner.cod_prov, this.personaBanner.cod_pais).subscribe(r => {
      this.parroquias = r;
    });
  }

  GETALL(){
    this.personaServices.obtenerAll().subscribe(r => {
      console.log(r);
    });
  }

}

