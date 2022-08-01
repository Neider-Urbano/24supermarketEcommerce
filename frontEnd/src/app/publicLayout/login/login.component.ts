import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PersonaService } from '../../Services/persona.service';
import { MatSnackBar } from '@angular/material/snack-bar'
import { CategoriaService } from 'src/app/Services/categoria.service';
import { ProductoService } from 'src/app/Services/producto.service';
import jsonCategorias from "../../../assets/jsondata/jsonCategorias.json";
import jsonUsuarioDefault from "../../../assets/jsondata/jsonUsuariosDefault.json";
import jsonProductos from "../../../assets/jsondata/jsonProductos.json"

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  

  public dataForm:FormGroup;
  private submitted: boolean = false;

  constructor(protected formBuilder: FormBuilder,  
    private svcPersona: PersonaService, 
    private snackBar:MatSnackBar,
    private svcCategoria: CategoriaService,
    private svcProducto: ProductoService) { 
      this.dataForm = this.formBuilder.group({
        cedula:['', [Validators.required]],
        password:['', [Validators.required]],
      });
  }

  ngOnInit(): void {
    this.svcCategoria.getCategorias().subscribe((value)=>{
      const dataObject = Object.values(value);
      if(dataObject[2].length<1){
        this.crearCategorias();
      }
    })
    this.svcPersona.getPersonas().subscribe((value)=>{
      const dataObject = Object.values(value);
      if(dataObject[2].length<1){
        this.crearPersonas();
      }
    })
    setTimeout(()=>{this.svcProducto.getProductos().subscribe((value)=>{
      const dataObject = Object.values(value);
      if(dataObject[2].length<1){
        this.crearProductos()
      }
    })},2000)
    setTimeout(()=>{
      alert("Has login con la siguiente credencial")
      alert("Cedula: 12345 , Password: 12345")
    },3000)
  }
  crearCategorias(){
    jsonCategorias.map((categoria: any) => {
      this.svcCategoria.crearCategoria(categoria).subscribe()
   })
  }
  crearPersonas(){
    jsonUsuarioDefault.map((persona: any) => {
      this.svcPersona.crearPersona(persona).subscribe()
   })
  }
  crearProductos(){
    jsonProductos.map((producto: any) => {
      this.svcProducto.crearProducto(producto, producto.categoriaid).subscribe()
   })
  }
  onClickIngresar() {
    this.submitted = true;
    if (this.submitted && this.dataForm.invalid){
      this.alertaOpen('Faltan datos obligatorios');
      return;
    }
    this.svcPersona.getPersonaIdContraseña(Number(this.dataForm.value.cedula), this.dataForm.value.password).subscribe(value => {
      const dataObject = Object.values(value);
      if(dataObject[2]!==null && dataObject[2]!==undefined){
        localStorage.clear();
        localStorage.setItem("cedulaUser", dataObject[2].cedula);
        localStorage.setItem("nameUser", dataObject[2].nombre);
        window.location.href="/home";
      }else{
        this.alertaOpen("Error al validar sus datos");
      }
    });
    this.dataForm.reset();
  }

  alertaOpen(mensaje: any){
    this.snackBar.open(mensaje, 'Ok', {
      horizontalPosition:'center',
      verticalPosition:'bottom',
      duration:5000
    })
  }
}
