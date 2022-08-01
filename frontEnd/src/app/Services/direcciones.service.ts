import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ResponseDto } from '../Shared/model/ResponseDto';

@Injectable({
  providedIn: 'root'
})
export class DireccionesService {
  private path =  `${environment.urlServerSkill}/direcciones`
  constructor(
    private http: HttpClient
  ) { }
  
  getDireccionesPersonaid(personaid: any):Observable<ResponseDto>{
    return this.http.get<ResponseDto>(`${this.path}/listar/persona/${personaid}`)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }
  postDireccionPersona(cedula: any, direccion:any):Observable<ResponseDto>{
    return this.http.post<ResponseDto>(`${this.path}/crear/${cedula}`, direccion)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }
}
