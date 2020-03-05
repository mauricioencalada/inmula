import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PreguntasService {

  url = environment.urlServer + 'preguntas/';

  constructor(private http: HttpClient) {
  }

  getPregunta() {
    return this.http.get(this.url + 'obtenerPreguntas');
  }

  getPreguntaById(id) {
    return this.http.get(this.url + 'obtenerId/' + id);
  }

  guardarPregunta(pregunta) {
    return this.http.post(this.url + 'crearPregunta', pregunta);
  }

}
