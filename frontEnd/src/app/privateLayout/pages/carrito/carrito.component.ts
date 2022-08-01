import { Component, OnInit } from '@angular/core';
import { Carrito } from 'src/app/Shared/model/Carrito';
import { CarritoService } from 'src/app/Services/carrito.service';
import {MatDialog} from '@angular/material/dialog';
import { EliminarconfirmdialogComponent } from '../../components/eliminarconfirmdialog/eliminarconfirmdialog.component';
import { MatSnackBar } from '@angular/material/snack-bar'

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements OnInit {
  displayedColumns: string[] = ['id','cedula', 'producto_id', "cantidad_producto",'nombre_producto', 'acciones']
  dataSource!:Carrito[];

  constructor(private srvCarrito: CarritoService,
    public dialog:MatDialog,private snackBar:MatSnackBar
  ) {}

  ngOnInit(): void {
    this.getCarrito();
  }
  getCarrito(){
    this.srvCarrito.getCarrito(localStorage.getItem("cedulaUser")).subscribe(value => {
      const dataObject = Object.values(value);
      if (dataObject[0] == 200) {
        this.dataSource = dataObject[2];
      }
    });
  }
  alertaOpen(mensaje: any){
    this.snackBar.open(mensaje, 'Ok', {
      horizontalPosition:'center',
      verticalPosition:'bottom',
      duration:5000
    })
  }

  onClickEditar(carrito: any){
    console.log(carrito)
  }
  eliminarCarrito(carritoid: number){
    const dialogRef=this.dialog.open(EliminarconfirmdialogComponent, {
      data: {title:"Eliminar de esta lista",
      button: "Eliminar"}
    });
    dialogRef.afterClosed().subscribe(res=>{
      if(res!==true){ 
        this.srvCarrito.eliminarCarrito(carritoid).subscribe(value => {
          const dataObject = Object.values(value);
          if(dataObject[0] == 201){
            this.alertaOpen("Se ha eliminado");
            this.getCarrito();
          }else{
            this.alertaOpen('¡Error!, no se pudo eliminar');
          }
        });
      }
    })
   
  }
}
