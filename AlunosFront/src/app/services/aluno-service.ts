import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { AlunoModel } from '../models/alunoModel';


@Injectable({
  providedIn: 'root'
})
export class AlunoService {
  private http = inject(HttpClient)
  private baseUrl = 'http://localhost:8080/alunos'
  private alunos : AlunoModel[] = [];

  listar() : Observable<AlunoModel[]>{
      return this.http.get<AlunoModel[]>(`${this.baseUrl}/listar`).
      pipe(catchError(this.handle))
  }


  adicionar(produto: AlunoModel) :  Observable<AlunoModel>{
    return this.http.post<AlunoModel>(`${this.baseUrl}/salvar`, produto)
    .pipe(catchError(this.handle))
  }


  remover(id: string): Observable<string> {
    return this.http.post(`${this.baseUrl}/apagar/${id}`, null,
     {responseType: 'text'}).pipe(catchError(this.handle))
  }

  editar(id: string, produto: AlunoModel): Observable<AlunoModel>{
    return this.http.post<AlunoModel>(`${this.baseUrl}/editar/${id}`, produto)
    .pipe(catchError(this.handle));
  }

  // delete(id: string){
  //   return this.delete(`${this.baseUrl}/apagar/${id}`, {id})
  // }

  private handle(err: HttpErrorResponse){
    const msg = err.error?.message || err.error?.erro || err.message
    return throwError(() => new Error(msg));
  }
}
