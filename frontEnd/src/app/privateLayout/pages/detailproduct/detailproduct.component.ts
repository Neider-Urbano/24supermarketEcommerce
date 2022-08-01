import { Component, OnInit } from '@angular/core';
import { ProductoService } from 'src/app/Services/producto.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Productos } from 'src/app/Shared/model/Productos';
import {MatDialog} from '@angular/material/dialog';
import { FacturarproductoComponent } from '../../components/facturarproducto/facturarproducto.component';
import { Direcciones } from 'src/app/Shared/model/Direcciones';
import { DireccionesService } from 'src/app/Services/direcciones.service';
import { Factura } from 'src/app/Shared/model/Facturas';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar'
import { CarritoService } from 'src/app/Services/carrito.service';

@Component({
  selector: 'app-detailproduct',
  templateUrl: './detailproduct.component.html',
  styleUrls: ['./detailproduct.component.css']
})
export class DetailproductComponent implements OnInit {
  public productos!:Productos[]
  public direcciones!:Direcciones[]
  public Factura!:Factura[]
  public myForm:FormGroup;
  private submitted: boolean = false;

  public categoria!:""
  public clickCompar:boolean=false;
  public clickAgregarDireccion:boolean=false;

  constructor(protected formBuilder: FormBuilder,
    private srvProducto : ProductoService, 
    private srvDirecciones : DireccionesService, 
    private srvCarrito : CarritoService, 
    private activateRouter: ActivatedRoute,
    private snackBar:MatSnackBar,
    public dialog:MatDialog ) { 
      this.myForm = this.formBuilder.group({
        facturaid:[""],
        precio:[],
        cantidad:['', [Validators.required]],
        direccion:['seleccione', [Validators.required]],
        fechafactura:[new Date(), [Validators.required]],
        personaid:[localStorage.getItem("cedulaUser"), [Validators.required]],
        formapago:['seleccione', [Validators.required]],
        numerocuenta:['', [Validators.required]],
        productoid:[this.activateRouter.snapshot.params['productoid'], [Validators.required]],
      });
    }

  ngOnInit(): void {
      this.srvProducto.getProductoId(this.activateRouter.snapshot.params['productoid']).subscribe(value=>{
        const dataObject = Object.values(value);
        if (dataObject[0] == 200){
          this.productos = [dataObject[2]];
          this.categoria=dataObject[2].categorias.categoria;
        }
      });
      this.srvDirecciones.getDireccionesPersonaid(localStorage.getItem("cedulaUser")).subscribe(value=>{
        const dataObject = Object.values(value);
        if (dataObject[0] == 200){
          this.direcciones=dataObject[2];
        }
      });
  }
  
  selectDireccion(e: any){
    const {value}=e.target;
    if(value==="agregar"){
      this.clickAgregarDireccion=true;
    }else{
      this.clickAgregarDireccion=false;
    }
  }

  onClickComprar():void{
    this.clickCompar=!this.clickCompar;
  }

  alertaOpen(mensaje: any){
    this.snackBar.open(mensaje, 'Ok', {
      horizontalPosition:'center',
      verticalPosition:'bottom',
      duration:5000
    })
  }

  onClickGenerarFactura(){
    this.submitted = true;
    var {direccion,formapago, cantidad}=this.myForm.value
    if ((this.submitted && this.myForm.invalid) || direccion==="seleccione" || formapago==="seleccione" || cantidad==="" || cantidad===0){
      this.alertaOpen('Faltan datos obligatorios')
      return;
    }
    const dialogRef=this.dialog.open(FacturarproductoComponent, {
      data: [this.myForm.value,this.productos]
    });
    dialogRef.afterClosed().subscribe(res=>{
      if(res!==true){
        const objectCarrito={
          cedula:this.myForm.value.personaid,
          productoid:this.productos[0].productoid,
          cantidadproducto: this.myForm.value.cantidad,
          nombreproducto:this.productos[0].nombre
        }
        this.srvCarrito.crearCarrito(objectCarrito).subscribe(value=>{
          const dataObject = Object.values(value);
          if (dataObject[0] == 201){
            this.alertaOpen('Se ha realizado la compra')
            var exiteDireccion=false;
            this.direcciones.map((direccion)=>{
              if(direccion.direccionid.toString()===this.myForm.value.direccion){
                exiteDireccion=true;
              }
            })
            if(!exiteDireccion){
              this.srvDirecciones.postDireccionPersona(objectCarrito.cedula,
                {direccion:this.myForm.value.direccion}).subscribe();
            }
            this.myForm.reset();
            setTimeout(()=>{
              window.location.href="/home/products"
            },4500) 
          }else{
            this.alertaOpen('Error al realizar la compra')
          }
        });
      }
    })
  }
}
