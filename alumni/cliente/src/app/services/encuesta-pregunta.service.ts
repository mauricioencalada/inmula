import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EncuestaPreguntaService {

  url = environment.urlServer + 'encuesta/';

  constructor(private http: HttpClient) {

  }

  getEncuesta() {
    return this.http.get(this.url + 'obtenerEncuestas');
  }

  getEncuestaPregunta() {
    return this.http.get(environment.urlServer + 'encuestaPregunta/obtenerEncuestaPregunta');
  }

  guardarEncuestaPregunta(encuestaPregunta) {
    return this.http.post(environment.urlServer + 'encuestaPregunta/crearEncuestaPregunta', encuestaPregunta);
  }

  getEncuestaPreguntaById(id) {
    return this.http.get(this.url + 'obtenerId/' + id);
  }

}
