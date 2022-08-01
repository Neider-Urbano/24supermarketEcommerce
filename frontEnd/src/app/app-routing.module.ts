import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarritoComponent } from './privateLayout/pages/carrito/carrito.component';
import { DetailproductComponent } from './privateLayout/pages/detailproduct/detailproduct.component';
import { MisproductosComponent } from './privateLayout/pages/misproductos/misproductos.component';
import { NewproductComponent } from './privateLayout/pages/newproduct/newproduct.component';
import { ProductsComponent } from './privateLayout/pages/products/products.component';
import { PrivateLayoutComponent } from './privateLayout/privatelayout.component';
import { Error404Component } from './publicLayout/error404/error404.component';
import { LoginComponent } from './publicLayout/login/login.component';
import { RegisterComponent } from './publicLayout/register/register.component';
import { MicuentaComponent } from './privateLayout/pages/micuenta/micuenta.component';
import { AuthorizatedGuard } from './Shared/guards/AuthorizatedGuard';

const routes: Routes = [
  {path: "", pathMatch: "full", redirectTo:  "login"},
  {path: "login", component: LoginComponent,
  canActivate:[AuthorizatedGuard]},
  {path: "register", component: RegisterComponent},
  {path: "home", component: PrivateLayoutComponent, 
    children:[
      {path: "", pathMatch: "prefix", redirectTo:  "products"},
      {path: "products", component: ProductsComponent},
      {path: "newproduct", component: NewproductComponent},
      {path: "misproducts", component: MisproductosComponent},
      {path: "carrito", component: CarritoComponent},
      {path: "detailproduct/:productoid", component: DetailproductComponent},
      {path: "micuenta", component: MicuentaComponent},
    ]
  },
  {path: "**", component: Error404Component}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
