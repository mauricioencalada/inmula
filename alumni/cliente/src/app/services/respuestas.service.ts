import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RespuestasService {

  url = environment.urlServer + 'respuestas/';

  constructor(private http: HttpClient) {
  }

  getResponse() {
    return this.http.get(this.url + 'obtenerRespuestas');
  }

  saveRespuestas(respuesta) {
    return this.http.post(this.url + 'crearRespuesta', respuesta);
  }

  updateRespuesta(respuesta) {
    return this.http.put(this.url + 'actualizarRespuesta/' + respuesta.id, respuesta);
  }
}
