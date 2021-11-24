import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ProductosComponent } from './productos.component';

@Injectable({
  providedIn: 'root'
})

export class FileUploadService {

  // API url
  baseApiUrl = "http://localhost:8080/api/productos";

  //inicializando objeto http
  constructor(private http: HttpClient) { }

  //variable auxiliar que almacena resultados de cada envio
  resultados = Array();

  // Retorna un objeto observable
  upload(file: any): Promise<any[]> {
    return new Promise<any[]>((resolve, reject) => {
      //leyendo el contenido
      var reader = new FileReader();
      reader.onloadend = (e) => {

        let lines = reader.result as string;

        let separados = lines.split("\n");

        for (let lineaactual of separados) {
          let columnas = lineaactual.split(",", 5);
          this.http.post(
            this.baseApiUrl,
            {
              nombreproducto: columnas[0],
              nitproveedor: columnas[1],
              preciocompra: columnas[2],
              ivacompra: columnas[3],
              precioventa: columnas[4]
            },
            { observe: 'response' }).subscribe(
              (response: any) => {
                let resaux = [];
                resaux[0] = response.status;
                this.resultados.push(resaux);
              }
            );
        }
        //console.log(this.resultados);
        resolve(this.resultados);
      };
      reader.readAsText(file);
    });
  }
}
