import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {PersonaService} from 'src/app/services/persona.service';
import {PersonaBanner} from 'src/app/model/PersonaBanner';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  id: any = sessionStorage.getItem('cedula');
  ecivil: any = [];
  paises: any = [];
  provincias: any = [];
  cantones: any = [];
  parroquias: any = [];
  array: any;
  personaBanner: any;
  x: any;


  constructor(private route: Router, private personaServices: PersonaService) {
    this.personaBanner = new PersonaBanner();
    this.personaServices.obtenerPais().subscribe(res => {
      this.paises = res;
    });
    this.obtenerPersona();
  }


  ngOnInit() {
  }

  obtenerPersona() {
    console.log(this.id);
    if (this.id !== null) {
      this.personaServices.obtenerPersona(this.id).subscribe(r => {
        this.personaBanner = r[0];
        let array1 = this.personaBanner.nombres.split(', ');
        this.personaBanner.apellido = array1[0];
        this.personaBanner.nombre = array1[1];
      });
    }
  }

  logout() {
    sessionStorage.clear();
    this.personaBanner = new PersonaBanner();
    this.route.navigate(['/home']);
  }
}
