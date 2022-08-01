import { Component, OnInit} from '@angular/core';
import { ProductoService } from 'src/app/Services/producto.service';
import { Productos } from 'src/app/Shared/model/Productos';
import {MatDialog} from '@angular/material/dialog';
import { ConfirmdialogComponent } from '../../components/confirmdialog/confirmdialog.component';
import { EliminarconfirmdialogComponent } from '../../components/eliminarconfirmdialog/eliminarconfirmdialog.component';

@Component({
  selector: 'app-misproductos',
  templateUrl: './misproductos.component.html',
  styleUrls: ['./misproductos.component.css']
})
export class MisproductosComponent implements OnInit {
  displayedColumns: string[] = ['id','nombre', 'precio', "categoria",'cantidad', 'acciones']
  dataSource!:Productos[];

  constructor(private srvProducto: ProductoService,
    public dialog:MatDialog
  ) {}

  ngOnInit(): void {
    this.getProductosPorCedula();
  }

  getProductosPorCedula(){
    this.srvProducto.getProductosPorCedula(localStorage.getItem("cedulaUser"))
      .subscribe(value => {
        const dataObject = Object.values(value);
        if (dataObject[0] == 200) {
          this.dataSource = dataObject[2];
        }
      });
  }

  eliminarProducto(productoid: any){
    const dialogRef=this.dialog.open(EliminarconfirmdialogComponent, {
      data: {title:"Eliminar de esta lista",
      button: "Eliminar"} 
    });
    dialogRef.afterClosed().subscribe(res=>{
      if(res!==true){ 
        this.srvProducto.eliminarProducto(productoid).subscribe(value => {
          const dataObject = Object.values(value);
          if (dataObject[0] == 201) {
            this.getProductosPorCedula();
          }
        });
      }
    })
  }

  onClickEditar(element:any):void{
    const dialogRef=this.dialog.open(ConfirmdialogComponent, {
      data: element
    });
    dialogRef.afterClosed().subscribe(res=>{
      if(res!==true){
        this.getProductosPorCedula();
      }
    })
  }
}

