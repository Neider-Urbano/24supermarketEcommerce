import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-facturarproducto',
  templateUrl: './facturarproducto.component.html',
  styleUrls: ['./facturarproducto.component.css']
})
export class FacturarproductoComponent implements OnInit {
  public preciototalcompra=1;
  constructor(
    public dialogRef: MatDialogRef<FacturarproductoComponent>,
    @Inject(MAT_DIALOG_DATA) public message: any
  ) {
    this.preciototalcompra=message[1][0].precio*message[0].cantidad;
  }

ngOnInit(): void {
}
onClickActualizar():void{
  this.dialogRef.close();
}
}