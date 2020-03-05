import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  titulo = "¿Quienes Somos?";
  titulo1 = "🎓 Alumni";
  titulo2 = "Espe"
  subtitulo = "Misión";
  subtitulo1 = "Objetivo";
  texto = 'Establecer un sistema permanente de interacción con el graduado y su entorno, para obtener información sobre el uso del resultado del proceso formativo y generar resultados que influyan directamente sobre los diseños o rediseños de las carreras y programas de posgrado y de los procesos formativos en general, mediante la aplicación de métodos y procedimientos ágiles, dinámicos y sencillos a fin de identificar las necesidades de formación específica en función de las demandas de la sociedad y del conocimiento del uso futuro del profesional.'
  texto1='Mejorar la gestión de la información de los graduados, permitiendo a la Universidad obtener información confiable y pertinente sobre la ubicación y las actividades que desempeñan en el ámbito laboral, el grado de satisfacción y nivel de exigencia de los empleadores en el desempeño profesional, lo que permitirá apoyar la toma de decisiones y el mejoramiento académico de la Universidad de las Fuerzas Armadas “ESPE”.'
 

  ngOnInit() {
  }

}
