import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar'
import { ProductoService } from '../../../Services/producto.service';

@Component({
  selector: 'app-confirmdialog',
  templateUrl: './confirmdialog.component.html',
  styleUrls: ['./confirmdialog.component.css']
})
export class ConfirmdialogComponent implements OnInit {
  public myForm:FormGroup;

  constructor(
    public dialogRef: MatDialogRef<ConfirmdialogComponent>,
    @Inject(MAT_DIALOG_DATA) public message: any,
    protected formBuilder: FormBuilder,  
    private svcProducto: ProductoService,
    private snackBar:MatSnackBar
  ) { this.myForm = this.formBuilder.group({
    productoid:[message.productoid],
    cedula:[message.cedula],
    nombre:[message.nombre, [Validators.required]],
    precio:[message.precio, [Validators.required]],
    cantidad:[message.cantidad, [Validators.required]],
    categoria:[message.categorias.categoriaid, [Validators.required]],
    descripcion:[message.descripcion,],
    imgurl:[message.imgurl,],
  });
}

  ngOnInit(): void {
  }
  onClickActualizar():void{
    if ((this.myForm.invalid) || this.myForm.value.categoria==="seleccione" || this.myForm.value.categoria===""){
      this.alertaOpen('Faltan datos obligatorios');
      return;
    }
    if(this.myForm.value.imgurl===""){
      this.myForm.value.imgurl="https://img.freepik.com/vector-gratis/coleccion-elementos-dibujados-mano-estudio-musica_23-2147567711.jpg?w=740&t=st=1659018808~exp=1659019408~hmac=d60625a6ec4cf1e331e1fc109bf245747ef9dca6f512a7753b342e421bb30806"
    }
    this.svcProducto.actualizarProducto(this.myForm.value, this.myForm.value.productoid).subscribe(
      value=>{
        const dataObject = Object.values(value);
        if (dataObject[0] && dataObject[0] == 201){
          this.alertaOpen('Producto actualizado');
          this.myForm.reset();
        }else{
          this.alertaOpen('¡Error!, no se actualizo el producto');
        }
      }
    )
    this.dialogRef.close();
  }
  alertaOpen(mensaje: any){
    this.snackBar.open(mensaje, 'Ok', {
      horizontalPosition:'center',
      verticalPosition:'bottom',
      duration:5000
    })
  }
}
