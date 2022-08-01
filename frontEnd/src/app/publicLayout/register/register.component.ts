import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar'
import { PersonaService } from '../../Services/persona.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public dataForm:FormGroup;
  private submitted: boolean = false;

  constructor(protected formBuilder: FormBuilder, 
    private svcPersona: PersonaService, 
    private snackBar:MatSnackBar) { 
      this.dataForm = this.formBuilder.group({
        nombre:['', [Validators.required]],
        cedula:['', [Validators.required]],
        email:['', [Validators.required]],
        password:['', [Validators.required]],
      });
  }
  
  ngOnInit(): void {
  }
  
  onClickCrear(){
    this.submitted = true;
    if (this.submitted && this.dataForm.invalid){
      this.alertOpen('Faltan datos obligatorios')
      return;
    }

    this.svcPersona.crearPersona(this.dataForm.value).subscribe(
      value=>{
        const dataObject = Object.values(value);
        if (dataObject[0] && dataObject[0] == 201){
          this.dataForm.patchValue = dataObject[2];
          this.alertOpen(dataObject[1])
          this.dataForm.reset();
        }else{
          this.alertOpen('Error al registrar sus datos')
          return;
        }
      }
    )
  }
  alertOpen(message: any){
    this.snackBar.open(message, 'Ok', {
      horizontalPosition:'center',
      verticalPosition:'bottom',
      duration:5000
    })
  }
}
