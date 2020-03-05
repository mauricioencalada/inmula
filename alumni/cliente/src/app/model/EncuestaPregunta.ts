import {Respuesta} from './Respuesta';
import {Encuesta} from './Encuesta';
import {Preguntas} from './Preguntas';

export class EncuestaPregunta {
  id?: number;
  fechaRespuesta?: any;
  respuesta: Respuesta;
  encuesta: Encuesta;
  preguntas: Preguntas;
  estado: any;
}
