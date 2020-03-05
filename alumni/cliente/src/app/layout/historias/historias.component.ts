import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-historias',
  templateUrl: './historias.component.html',
  styleUrls: ['./historias.component.css']
})
export class HistoriasComponent implements OnInit {

  titulo="cuentanos";
  titulo1="tu";
  titulo2="historia";
  titulo3="y éxitos profesionales";
  subtitulo="ROLANDO LÓPEZ ALCALDE DEL CANTÓN ANTONIO ANTE ALUMNI DE INGERNIERÍA  COMERCIAL";
  subtitulo1="HUGO N. VILLEGAS PICO ALUMNI DE INGENIERÍA EN AUTOMATIZACIÓN Y CONTROL"
  texto="Nací en la ciudad de Atuntaqui,  mis estudios superiores los realicé en la ciudad de Quito y me gradué como Ingeniero Comercial en la Universidad Politécnica del Ejército o Universidad de las Fuerzas Armadas ESPE. Trabajé en la firma auditora Price Waterhouse, donde tuve la oportunidad de formarme como  profesional y como emprendedor.  Gracias al ejemplo de mis padres, que son unos de los pioneros en el sector textil, y  junto a mi familia formamos Createxsa Industria Textil una empresa con más de 15 años de trayectoria que da fuentes de trabajo dignas a 83 familias.";
  texto1="        Mi lema es  “Sé que dificultades te pueden hacer, o te pueden romper, todos los días tienes un nuevo reto de vida” y estoy convencido que mi Cantón es un lugar de Buena Gente, Solidaria y Comprometida con la comunidad.  Hoy he sido electo Alcalde del Cantón Antonio Ante, provincia de Imbabura y tengo muchos proyectos de los cuales beneficiarán miles de familias Anteñas. ";
  texto2="RecibÍ el grado de Ingeniero en Electrónica, Automatización y Control de la Universidad de las Fuerzas Armadas - ESPE, en 2008; el grado de Máster en Ciencias de ingeniería eléctrica de Iowa State University, Ames, IA, USA, en 2011; y el grado de Ph.D. en ingeniería eléctrica y computación de Purdue University, West Lafayette, IN, USA, en 2016. Mi  trabajo de Doctorado, sobre la respuesta dinámica de turbinas eólicas durante transitorios inciertos de voltaje, fue reconocido con: (i) un best paper award in the IEEE Transactions on Energy Conversion y (ii) un IEEE-PES Prize Paper Award.  El último es el premio más prestigioso otorgado a una publicación técnica por parte de la IEEE Power & Energy Society.";
  texto3="Fui Supervisor de Mantenimiento Eléctrico de la Central Térmica Guangopolo, CELEC EP-Termopichincha, Ecuador durante 2007–2009 e Investigador de Postdoctorado en Purdue University, West Lafayette, IN, USA durante 2016–2017. Actualmente, soy investigador de Postdoctorado de ingeniería de potencia en el National Renewable Energy Laboratory, Golden, CO, USA, donde desarrollo tecnologías y métodos numéricos para facilitar la incorporación a gran escala de fuentes de energía renovable no convencional a los sistemas de potencia.";
  texto4="Mis intereses de investigación están en la intersección de conversión de energía renovable a electricidad, ingeniería de sistemas eléctricos de potencia, electrónica de potencia, sistemas de control, y análisis computacional de sistemas dinámicos con incertidumbres. Soy  miembro del IEEE y soy  evaluador de artículos científicos en varias revistas patrocinadas por la IEEE Power & Energy Society."


  constructor() { }

  ngOnInit() {
  }

}
