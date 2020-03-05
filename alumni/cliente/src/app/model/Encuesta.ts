
import { TipoEncuesta } from './TipoEncuesta';

export class Encuesta{
    id?:number;
    nombre:string;
    descripcion:string;
    fechaInicio:string;
    fechaFin:string;
    estado:string;
    tipoEncuesta:TipoEncuesta;
}