import { Component, OnInit } from '@angular/core';
import { FileUploadService } from './file-upload.service';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {


  constructor(private objetohttp: HttpClient, private fileUploadService: FileUploadService) { }

  //variable receptora de documentos
  res: any;
  //variable contenedora de contenidos
  contenido: any;
  //url api get
  urlapiGET: string = "http://localhost:8080/api/productos";

  ngOnInit(): void {

    this.res = this.objetohttp.get(this.urlapiGET);

    this.res.subscribe((datos: any[]) => {
      this.contenido = datos;
      console.log(this.contenido);
    });

  }

  /////////////////////////////////////////////////////////////////
  codigoRespuesta!: number;
  res2: any;
  nombreproducto!:string;
  nitproveedor!:string;
  ivacompra!:string;
  preciocompra!:string;
  precioventa!:string;
  postData() {
    this.objetohttp.post<any>(
      'http://localhost:8080/api/productos',
      {
        nombreproducto:this.nombreproducto,
        nitproveedor: this.nitproveedor,
        ivacompra: this.ivacompra,
        preciocompra: this.preciocompra,
        precioventa: this.precioventa
      },
      { observe: 'response' }
    ).subscribe(response=>{
      this.codigoRespuesta=response.status;
      this.res2=response;
      console.log(this.codigoRespuesta)
    console.log(this.res2)
    });
    

  }


  file!: File;

  resultados: any;

  cargado: boolean = false;

  onChange(event: any) {
    this.objetohttp.delete("http://localhost:8080/api/productos").subscribe(data => {
      console.log(data);
    });
    this.file = event.target.files[0];
  }

async onUpload() {
  console.log(this.file);
  this.cargado = true;
  this.resultados = await this.fileUploadService.upload(this.file);
  console.log(this.resultados);
}

refrescar(){
  window.location.reload();
}


}
