import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Item } from '../models/item/item';
import { environment } from '../enviroments/enviroment.dev';

@Injectable({
  providedIn: 'root'
})
export class ProdutosService {

  //private readonly apiUrl = `${environment.apiUrl}/product`;

  API = environment.SERVIDOR + "/produto";

  constructor(private http: HttpClient) {}

  getItens(): Observable<Item[]> {
    return this.http.get<Item[]>(this.API);
  }

  getItemById(id: string): Observable<Item> {
    return this.http.get<Item>(`${this.API}/${id}`);
  }

  criarItem(item: Item): Observable<Item> {
    return this.http.post<Item>(this.API, item);
  }

  atualizarItem(id: string, item: Item): Observable<Item> {
    return this.http.put<Item>(`${this.API}/${id}`, item);
  }

  deletarItem(id: string): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }

  deletarTodosItens(): Observable<void> {
    return this.http.delete<void>(`${this.API}/todos`);
  }
}