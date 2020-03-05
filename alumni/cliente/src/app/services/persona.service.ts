import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment.prod';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {

  url = environment.bannerURL;
  urlServer = environment.urlServer + 'persona/';

  constructor(private http: HttpClient) {
  }

  obtenerPersona(cedula) {
    return this.http.get(this.url + 'user/alumni/' + cedula);
  }

  obtenerEstadoCivil() {
    return this.http.get(this.url + 'ecivil');
  }

  obtenerPais() {
    return this.http.get(this.url + 'paises');
  }

  obtenerProvincia() {
    return this.http.get(this.url + 'provincias');
  }

  obtenerCanton(prov) {
    return this.http.get(this.url + 'cantones/' + prov);
  }

  obtenerParroquia(canton, prov, pais) {
    return this.http.get(this.url + 'parroquia/' + canton + '/' + prov + '/' + pais);
  }

  obtenerCarrera(cedula) {
    return this.http.get(this.url + 'carreras/' + cedula);
  }

  iniciarSesion(persona) {
    return this.http.post(this.urlServer + 'auth', persona);
  }

  registrarse(persona) {
    return this.http.post(this.urlServer + 'crearPersona', persona);
  }
  obtenerAll(){
    return this.http.get(this.urlServer + 'obtenerPersona');
  }

}
