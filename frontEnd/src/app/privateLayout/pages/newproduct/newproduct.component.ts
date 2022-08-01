import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar'
import { ProductoService } from '../../../Services/producto.service';

@Component({
  selector: 'app-newproduct',
  templateUrl: './newproduct.component.html',
  styleUrls: ['./newproduct.component.css']
})
export class NewproductComponent implements OnInit {
  public myForm:FormGroup;

  constructor(protected formBuilder: FormBuilder, 
    private svcProducto: ProductoService,
    private snackBar:MatSnackBar) { 
      this.myForm = this.crearFormControl();
  }
  
  ngOnInit(): void {
  }
  crearFormControl(){
    return this.formBuilder.group({
      productoid:['',],
      cedula:[localStorage.getItem("cedulaUser"), [Validators.required]],
      nombre:['', [Validators.required]],
      precio:['', [Validators.required]],
      cantidad:['', [Validators.required]],
      categoria:['seleccione', [Validators.required]],
      descripcion:['',],
      imgurl:['',],
    });
  }
  onClickCrear(){
    if ((this.myForm.invalid) || this.myForm.value.categoria==="seleccione" || this.myForm.value.categoria===""){
      this.alertaOpen('Faltan datos obligatorios');
      return;
    }
    if(this.myForm.value.imgurl===""){
      this.myForm.value.imgurl="https://img.freepik.com/vector-gratis/coleccion-elementos-dibujados-mano-estudio-musica_23-2147567711.jpg?w=740&t=st=1659018808~exp=1659019408~hmac=d60625a6ec4cf1e331e1fc109bf245747ef9dca6f512a7753b342e421bb30806"
    }
    this.svcProducto.crearProducto(this.myForm.value, this.myForm.value.categoria).subscribe(
      value=>{
        const dataObject = Object.values(value);
        if (dataObject[0] && dataObject[0] == 201){
          this.myForm.patchValue = dataObject[2];
          this.alertaOpen(dataObject[1]);
          this.myForm = this.crearFormControl();
        }else{
          this.alertaOpen('Error al registrar el producto');
        }
      }
    )
  }
  alertaOpen(mensaje: any){
    this.snackBar.open(mensaje, 'Ok', {
      horizontalPosition:'center',
      verticalPosition:'bottom',
      duration:5000
    })
  }
}
