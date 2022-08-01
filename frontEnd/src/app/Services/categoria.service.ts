import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ResponseDto } from '../Shared/model/ResponseDto';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private path =  `${environment.urlServerSkill}/categorias`
  constructor(
    private http: HttpClient
  ) { }
  
  crearCategoria(categoria: any):Observable<ResponseDto>{
    return this.http.post<ResponseDto>(`${this.path}/crear`, categoria)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }
  getCategorias():Observable<ResponseDto>{
    return this.http.get<ResponseDto>(`${this.path}/listar`)
    .pipe(map(
      value=>{
        return value
      }
    ));
  }
}
