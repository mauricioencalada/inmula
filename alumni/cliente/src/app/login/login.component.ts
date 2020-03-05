import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators, FormControl} from '@angular/forms';
import {Persona} from '../model/Persona';
import {PersonaService} from '../services/persona.service';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';
import {environment} from '../../environments/environment';
import {Roles} from '../model/Roles';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  registro = false;
  rPassword: any;
  persona: Persona = new Persona();
  roles: Roles = new Roles();
  tipoInput: any = 'password';


  constructor(private personaServices: PersonaService, private toastr: ToastrService, private route: Router, private router: ActivatedRoute) {
    this.route.navigate(['/web/surveys']);
  }

  ngOnInit() {
    this.GETALL();
  }

  login() {
    this.personaServices.iniciarSesion(this.persona).subscribe(r => {
      let pResponse: any = {};
      pResponse = r;
      this.toastr.success('Credenciales Correctas!', 'Logueado con Exito');
      sessionStorage.setItem('isLoggedin', 'true');
      sessionStorage.setItem('cedula', pResponse.personaBanner);
      sessionStorage.setItem('id', pResponse.id);
      this.route.navigate(['/web/surveys']);
      window.location.reload();
    }, error => {
      this.toastr.error('Credenciales Incorrectas!', 'Oops algo ha salido mal');
    });
  }

  register() {
    if (this.persona.personaBanner != undefined && this.persona.password != undefined && this.rPassword != undefined) {
      if (this.rPassword == this.persona.password) {
        this.personaServices.obtenerPersona(this.persona.personaBanner).subscribe(r => {
          let respuesta: any;
          respuesta = r;
          if (respuesta.length == 0) {
            this.toastr.error('Nose encontro su cédula!', 'Oops algo ha salido mal');
          } else {
            console.log(respuesta[0].cedula);
            this.persona.personaBanner = respuesta[0].cedula;
            this.roles.id = 2;
            this.persona.roles = this.roles;
            this.personaServices.registrarse(this.persona).subscribe(res => {
             this.login();
            }, error => {
              let resp: any;
              resp = error;
              this.toastr.error(resp.error + '!', 'Oops algo ha salido mal');
            });
          }
        });
      }
    }
  }

  mostrarContrasena(item) {
    if (item == 'password') {
      this.tipoInput = 'text';
    }
    if (item == 'text') {
      this.tipoInput = 'password';
    }
  }
  GETALL() {
    this.personaServices.obtenerAll().subscribe(r => {
      //console.log(r);
    });
  }
}
