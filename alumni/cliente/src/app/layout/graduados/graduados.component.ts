import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-graduados',
  templateUrl: './graduados.component.html',
  styleUrls: ['./graduados.component.css']
})
export class GraduadosComponent implements OnInit {

  titulo1='Graduados en el';
  titulo2= 'mundo';
  texto1='Vivir en otro país, debe ser todo un reto. Para nuestros Graduados ejercer su profesión fuera de su nación, aún más, al ser embajadores de la UNIVERSIDAD DE LAS FUERZAR ARMADAS ESPE del Ecuador';
  persona='ING. ALEXANDRA BASANTES';
  texto2='Con mucho esfuerzo y dedicación me gradué en la carrera de Ingeniería Mecánica de la Escuela Politécnica del Ejército hoy Universidad de las Fuerzas Armadas ESPE, en el año 2012; realicé mi maestría en el Departamento de ingeniería Civil y de Materiales de la Universidad de Illinois en Chicago(LUC) en el 2018. En el año de 2017 gané el segundo lugar en el concurso de Imagen de Investigación de la UIC 2017 con la fotografía de "Soldadura Inteligente" en la categoría de Ingeniería, Matemáticas y Ciencias físicas.';
  texto3='Mi investigación se basó en las técnicas tradicionales de soldadura y soldadura autónoma en "tiempo re al" para evitar defectos. Aborda la garantía de calidad de la soldadura en el desarrollar un sistema combinado de diagnóstico, decisión y control en tiempo real basado en la fusión de múltiples sensores y la metodología de aprendizaje automático. La principal innovación tecnológica de la investigación es que una máquina de soldadura podrá tomar decisiones inteligentes en respuesta a variables de proceso, perturbaciones y deterioro de las herramientas en tiempo real.'
  texto4:'La foto fue tomada el 28 de febrero de 2017, en el laboratorio. Representa la profesionalidad de comprenderlos fundamentos y el poder de la simplicidad. Tener algo de experiencia en soldadura me ha permitido sobresalir en mi investigación. También muestra el marcado contraste de la percepción y la realidad del arte de soldar, soldando entre metales a una corta distancia creando chispas de soldadura que vuelan. Trabajé para la empresa Chicago Ornamental Iron en Chicago, como Jefe de Control de Calidad.';

  constructor() { }

  ngOnInit() {
  }

}
