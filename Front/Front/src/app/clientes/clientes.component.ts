import { Component, OnInit } from '@angular/core';
import { ServiciosService } from '../servicios/servicios.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit {
  form !:FormGroup
  ocultar:boolean = false
  data:any
  boton:any = 'Agregar'
  constructor(private service:ServiciosService, private fb:FormBuilder) {
    this.form = this.fb.group({
      cedulacliente: ['',Validators.required],
      direccioncliente: ['',Validators.required],
      emailcliente: ['',Validators.required],
      id: [''],
      nombrecliente: ['',Validators.required],
      telefonocliente: ['',Validators.required],
    });
  }

  ngOnInit(): void {
    this.getAll()
    this.boton = 'Agregar'
  }
  reset() {
    this.form.reset()
    this.boton = 'Agregar'
  }

getAll(){
  this.service.getClient().subscribe(res=>{
    this.data =res
  })
}
viewForm(e:any){
  this.ocultar= true
  this.boton = 'Actualizar'
  this.form.patchValue({
   cedulacliente :e.cedulacliente,
    direccioncliente:e.direccioncliente,
    emailcliente:e.emailcliente,
    id:e.id,
    nombrecliente:e.nombrecliente,
    telefonocliente:e.telefonocliente
  })
  console.log(this.form)
}
delete(id:any){
  if(confirm('Estas Seguro de elimiar a este Cliente')){
    this.service.DeleteClient(id).subscribe(res=>{
      alert(res)
      this.ngOnInit()
    },err=>{
      alert(err)
    }
    )
  }
}

AddEdit(){
 const info={
    cedulacliente:this.form.value.cedulacliente,
    direccioncliente:this.form.value.direccioncliente,
    emailcliente:this.form.value.emailcliente,
    id:this.form.value.id,
    nombrecliente:this.form.value.nombrecliente,
    telefonocliente:this.form.value.telefonocliente
  }
console.log(this.form.value.id)
  if(this.form.value.id == null){
    console.log('Ingresar')
    console.log(this.form.value.id)
    this.service.AddClient(info).subscribe(res=>{
      this.form.reset()
      this.ocultar =false
      this.ngOnInit()
    })
  }else{
   this.service.EditClient(info.id, info).subscribe(res=>{
      console.log(res)
      this.form.reset()
      this.ocultar =false
      this.ngOnInit()
    })
  }
}
}
