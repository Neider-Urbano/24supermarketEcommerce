import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar'

@Component({
  selector: 'app-eliminarconfirmdialog',
  templateUrl: './eliminarconfirmdialog.component.html',
  styleUrls: ['./eliminarconfirmdialog.component.css']
})
export class EliminarconfirmdialogComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<EliminarconfirmdialogComponent>,
    @Inject(MAT_DIALOG_DATA) public message: any,
    protected formBuilder: FormBuilder, 
    private snackBar:MatSnackBar
  ) { }

  ngOnInit(): void {
  }
  onClickEliminar():void{
    this.dialogRef.close();
  }
}
