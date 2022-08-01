import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ResponseDto } from '../Shared/model/ResponseDto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private path =  `${environment.urlServerSkill}/productos`
  constructor(
    private http: HttpClient
  ) { }
  
  getProductos(): Observable<ResponseDto>{
    return this.http.get<ResponseDto>(`${this.path}/listar`)
      .pipe(map(
        value=>{
          return value
        }
      ));
  }

  getProductoId(productoid:any):Observable<ResponseDto>{
    return this.http.get<ResponseDto>(`${this.path}/buscar/${productoid}`)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }

  getProductosPorCategoria(categoriaid:any):Observable<ResponseDto>{
    return this.http.get<ResponseDto>(`${this.path}/listar/categoria/${categoriaid}`)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }

  getProductosPorCedula(cedula:any):Observable<ResponseDto>{
    return this.http.get<ResponseDto>(`${this.path}/listar/persona/${cedula}`)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }

  actualizarProducto(producto:any, productoid:string): Observable<ResponseDto>{
    return this.http.put<ResponseDto>(`${this.path}/actualizar/${productoid}`, producto)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }

  crearProducto(producto: any, categoriaid: number):Observable<ResponseDto>{
    return this.http.post<ResponseDto>(`${this.path}/crear/categoria/${categoriaid}`, producto)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }

  eliminarProducto(productoid: number):Observable<ResponseDto>{
    return this.http.delete<ResponseDto>(`${this.path}/delete/${productoid}`)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }

  eliminarProductoCedula(cedula: number):Observable<ResponseDto>{
    return this.http.delete<ResponseDto>(`${this.path}/delete/persona/${cedula}`)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }
}
