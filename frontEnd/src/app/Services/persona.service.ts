import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ResponseDto } from '../Shared/model/ResponseDto';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {
  
  private path =  `${environment.urlServerSkill}/persona`
  constructor(
    private http: HttpClient
  ) { }
  
  getPersonas(): Observable<ResponseDto>{
    return this.http.get<ResponseDto>(`${this.path}/listar`)
      .pipe(map(
        value=>{
          return value
        }
      ));
  }

  getPersonaCedula(cedula:number){
    return this.http.get<ResponseDto>(`${this.path}/buscar/${cedula}`)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }

  getPersonaIdContraseña(cedula:number, password:string){
    return this.http.get<ResponseDto>(`${this.path}/buscar/${cedula}/contraseña/${password}`)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }

  actualizarPersona(persona:any, cedula:number):Observable<ResponseDto>{
    return this.http.put<ResponseDto>(`${this.path}/actualizar/${cedula}`, persona)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }

  crearPersona(persona: any):Observable<ResponseDto>{
    return this.http.post<ResponseDto>(`${this.path}/crear`, persona)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }
  eliminarPersona(cedula: any):Observable<ResponseDto>{
    return this.http.delete<ResponseDto>(`${this.path}/eliminar/${cedula}`)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }
}
