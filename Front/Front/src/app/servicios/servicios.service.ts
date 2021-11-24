import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiciosService {
  allClient= 'http://localhost:8080/api/clientes'
  ByIdClient ='http://localhost:8080/api/clientes/'
  constructor(private http:HttpClient) { }
// Obtener todos los clientes
  getClient():Observable<any[]>{
   return  this.http.get<any[]>(this.allClient)
  }

  // Obtener Clientes Por id
  getByIdClient(id:number , form:any):Observable<any>{
    return  this.http.put<any>(`${this.ByIdClient}${id}`,form)
   }
// Ingresar clientes
AddClient(form:any):Observable<any>{
  return  this.http.post<any>(`${this.allClient}`, form)
 }
 // Actualizar Clientes
 EditClient(id:number,form:any):Observable<any>{
  return  this.http.put<any>(`${this.ByIdClient}${id}`, form)
 }

  // Eliminar Clientes
  DeleteClient(id:number):Observable<any>{
    return  this.http.delete<any>(`${this.ByIdClient}${id}`)
   }

}
