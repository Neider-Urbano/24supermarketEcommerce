import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { PrivatelayoutRoutingModule } from './privatelayout-routing.module';
import { PrivateLayoutComponent } from "./privatelayout.component";
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ProductsComponent } from './pages/products/products.component';
import { NewproductComponent } from './pages/newproduct/newproduct.component';
import { CarritoComponent } from './pages/carrito/carrito.component';
import { MisproductosComponent } from './pages/misproductos/misproductos.component';
import { DetailproductComponent } from './pages/detailproduct/detailproduct.component'

import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { ConfirmdialogComponent } from './components/confirmdialog/confirmdialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { FacturarproductoComponent } from './components/facturarproducto/facturarproducto.component';
import { EliminarconfirmdialogComponent } from './components/eliminarconfirmdialog/eliminarconfirmdialog.component';
import { MicuentaComponent } from './pages/micuenta/micuenta.component';


@NgModule({
  declarations: [
    PrivateLayoutComponent,
    HeaderComponent,
    FooterComponent,
    ProductsComponent,
    NewproductComponent,
    CarritoComponent,
    MisproductosComponent,
    DetailproductComponent,
    ConfirmdialogComponent,
    FacturarproductoComponent,
    EliminarconfirmdialogComponent,
    MicuentaComponent,
  ],
  imports: [
    BrowserModule,
    CommonModule,
    HttpClientModule,
    PrivatelayoutRoutingModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    MatDialogModule 
  ]
})
export class PrivatelayoutModule { }
