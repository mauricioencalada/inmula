import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-emprendimiento',
  templateUrl: './emprendimiento.component.html',
  styleUrls: ['./emprendimiento.component.css']
})
export class EmprendimientoComponent implements OnInit {
  
  titulo="Emprendimiento";
  titulo1="DENNIS BRITO MADRID";
  subtitulo="Conoce a nuestros graduados emprendedores. Si deseas emprender tu negocio, aquí te ayudamos.";
  txt="Soy Dennis Brito Madrid, agricultor por vocación, desde los años finales de la carrera me apasioné por la producción, exportación y procesamiento de productos no tradicionales, como la UVILLA, MORTIÑO, TAXO, entre muchas otras frutas; lo cual ha permitido formar varias empresas, dedicadas a la producción, procesamiento y generación de la cadena productiva de la UVILLA en el Ecuador y en el mundo."

  constructor() { }

  ngOnInit() {
  }

}
