import { Component, OnInit } from '@angular/core';
import { ProductoService } from 'src/app/Services/producto.service';
import { Productos } from 'src/app/Shared/model/Productos';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})

export class ProductsComponent implements OnInit {
  public productos!: Productos[]
  public productosAux!: Productos[]

  constructor(private srvProducto: ProductoService) { }

  ngOnInit(): void {
    this.srvProducto.getProductos().subscribe(value => {
      const dataObject = Object.values(value);
      if (dataObject[0] == 200) {
        this.productos = dataObject[2];
        this.productosAux = dataObject[2];
      }
    });
  }
  changeFn(e: any) {
    const { value } = e.target;
    if (value === "seleccione") {
      this.productos = this.productosAux;
    } else {
      this.productosPorCategoria(value);
    }
  }

  changeFnOrdenar(e: any) {
    const { value } = e.target;
    if (value === "seleccione") {
      this.productos = this.productosAux;
    } else {
      this.productos.sort(
        (firstObject: any, secondObject: any) =>
          (firstObject.nombre.toLocaleLowerCase()  > secondObject.nombre.toLocaleLowerCase()) ? 1 : -1
      );
      if(value==="2"){
        this.productos.sort(
          (firstObject: any, secondObject: any) =>
            (firstObject.nombre.toLocaleLowerCase() < secondObject.nombre.toLocaleLowerCase()) ? 1 : -1
        );
      }
      else if(value==="3"){
        this.productos.sort(
          (firstObject: any, secondObject: any) =>
            (firstObject.productoid < secondObject.productoid) ? 1 : -1
        );
      }else if(value==="4"){
        this.productos.sort(
          (firstObject: any, secondObject: any) =>
            (firstObject.productoid > secondObject.productoid) ? 1 : -1
        );
      }
    }
  }

  productosPorCategoria(categoriaid: number) {
    this.srvProducto.getProductosPorCategoria(categoriaid).subscribe(value => {
      const dataObject = Object.values(value);
      if (dataObject[0] == 200) {
        this.productos = dataObject[2];
      }
    });
  }
}
