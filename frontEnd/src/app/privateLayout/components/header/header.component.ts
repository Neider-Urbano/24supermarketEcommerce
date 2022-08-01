import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    ) { 
  }
  
  ngOnInit(): void {
  }

  cerrarCesion(){
    localStorage.clear();
    location.href ="http://localhost:4200/login";
  }
}
