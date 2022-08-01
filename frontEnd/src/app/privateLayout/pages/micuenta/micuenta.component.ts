import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar'
import { PersonaService } from 'src/app/Services/persona.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import { EliminarconfirmdialogComponent } from '../../components/eliminarconfirmdialog/eliminarconfirmdialog.component';
import { ProductoService } from 'src/app/Services/producto.service';
import { CarritoService } from 'src/app/Services/carrito.service';

@Component({
  selector: 'app-micuenta',
  templateUrl: './micuenta.component.html',
  styleUrls: ['./micuenta.component.css']
})
export class MicuentaComponent implements OnInit {
  public myForm:any;

  constructor(
    private svcPersona: PersonaService,
    private svcProducto: ProductoService,
    private svcCarrito: CarritoService,
    protected formBuilder: FormBuilder,
    public dialog:MatDialog,
    private snackBar:MatSnackBar) {
  }

  ngOnInit(): void {
    this.buscarPersona(localStorage.getItem("cedulaUser"))
  }

  buscarPersona(cedula: any){
    this.svcPersona.getPersonaCedula(cedula).subscribe(value=>{
      var dataObject = Object.values(value);
      if (dataObject[0] && dataObject[0] == 200){ 
        this.myForm = this.formBuilder.group({
          cedula:[localStorage.getItem("cedulaUser"),[Validators.required]],
          nombre:[dataObject[2].nombre, [Validators.required]],
          email:[dataObject[2].email, [Validators.required]],
          password:[dataObject[2].password, [Validators.required]],
          direcciones:[dataObject[2].direcciones, [Validators.required]],
        });
      }
    })
  }
  onClickActualizar(){
    const dialogRef=this.dialog.open(EliminarconfirmdialogComponent, {
      data: {title:"Se actualizara su cuenta",
      button: "Actualizar"}
    });
    dialogRef.afterClosed().subscribe(res=>{
      if(res!==true){
        this.svcPersona.actualizarPersona(this.myForm.value,this.myForm.value.cedula).subscribe(value => {
          const dataObject = Object.values(value);
          if (dataObject[0] == 200) {
            this.alertaOpen('¡Su cuenta fue actualizada!')
          }else{
            this.alertaOpen('¡Error al actualizar su cuenta!')
            return;
          }
          this.buscarPersona(this.myForm.value.cedula);
        });
      }});
  }
  onClickEliminar(){
    const dialogRef=this.dialog.open(EliminarconfirmdialogComponent, {
      data: {title:"Eliminar esta cuenta de usuario",
              button: "Eliminar"}
    });
    dialogRef.afterClosed().subscribe(res=>{
      if(res!==true){
        if(localStorage.getItem("cedulaUser")!=="12345"){
          this.svcPersona.eliminarPersona(this.myForm.value.cedula).subscribe(value => {
            const dataObject = Object.values(value);
            if (dataObject[0] == 201) {
              this.svcProducto.eliminarProductoCedula(this.myForm.value.cedula).subscribe(value => {
                const dataObject = Object.values(value);
                if (dataObject[0] == 201) {
                  this.svcCarrito.eliminarCarritoCedula(this.myForm.value.cedula)
                  .subscribe(value => {
                    const dataObject = Object.values(value);
                    if (dataObject[0] == 201) {
                      this.alertaOpen('Cuenta eliminada')
                      localStorage.clear();
                      setTimeout(()=>{
                        window.location.href="login"
                      }, 3000)
                    }else{
                      this.alertaOpen('¡Error al eliminar su cuenta!')
                      return;
                    }
                  })
                }else{
                  this.alertaOpen('¡Error al eliminar su cuenta!')
                  return;
                }
              })
            }else{
              this.alertaOpen('¡Error al eliminar su cuenta!')
              return;
            }
          });
        }else{
          this.alertaOpen('Cuenta eliminada')
          localStorage.clear();
          setTimeout(()=>{
            window.location.href="login"
          }, 3000)
        }
    }});
  }
  
  alertaOpen(mensaje: any){
    this.snackBar.open(mensaje, 'Ok', {
      horizontalPosition:'center',
      verticalPosition:'bottom',
      duration:5000
    })
  }
}
