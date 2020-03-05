import {Persona} from './Persona';
import { TipoBlog } from './TipoBlog';

export class ContenidoBlog {
  id?: number;
  contenido: string;
  fechaInicio: string;
  fechaFin: string;
  serial: string;
  persona: Persona;
  tipoBlog:TipoBlog;
}
