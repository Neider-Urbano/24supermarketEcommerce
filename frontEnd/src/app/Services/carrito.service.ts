import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ResponseDto } from '../Shared/model/ResponseDto';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  private path =  `${environment.urlServerSkill}/carrito`
  constructor(
    private http: HttpClient
  ) { }
  
  crearCarrito(carrito: any):Observable<ResponseDto>{
    return this.http.post<ResponseDto>(`${this.path}/crear`, carrito)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }

  getCarrito(cedula: any):Observable<ResponseDto>{
    return this.http.get<ResponseDto>(`${this.path}/listar/persona/${cedula}`)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }

  eliminarCarrito(carritoid: any):Observable<ResponseDto>{
    return this.http.delete<ResponseDto>(`${this.path}/eliminar/${carritoid}`)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }

  eliminarCarritoCedula(cedula: any):Observable<ResponseDto>{
    return this.http.delete<ResponseDto>(`${this.path}/eliminar/persona/${cedula}`)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }
}
