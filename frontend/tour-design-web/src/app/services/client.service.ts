import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { ClientModel } from '../models/client.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private baseUrl = '/client';

  constructor(private api: ApiService) {}

  list(): Observable<ClientModel[]> {
    return this.api.get<ClientModel[]>(this.baseUrl);
  }

  search(id: number): Observable<ClientModel> {
    return this.api.get<ClientModel>(`${this.baseUrl}/${id}`);
  }

  searchByQuery(query: string): Observable<ClientModel[]> {
    return this.api.get<ClientModel[]>(`${this.baseUrl}/searchByQuery?query=${query}`);
  }

  create(obj: ClientModel): Observable<ClientModel> {
    return this.api.post<ClientModel>(this.baseUrl, obj);
  }

  update(id: number, obj: ClientModel): Observable<ClientModel> {
    return this.api.put<ClientModel>(`${this.baseUrl}/${id}`, obj);
  }

  delete(id: number): Observable<void> {
    return this.api.delete<void>(`${this.baseUrl}/${id}`);
  }
}