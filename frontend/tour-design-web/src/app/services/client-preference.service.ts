import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { ClientPreferenceModel } from '../models/client-preference.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientPreferenceService {
  private baseUrl = '/client-preference';

  constructor(private api: ApiService) {}

  list(): Observable<ClientPreferenceModel[]> {
    return this.api.get<ClientPreferenceModel[]>(this.baseUrl);
  }

  search(id: number): Observable<ClientPreferenceModel> {
    return this.api.get<ClientPreferenceModel>(`${this.baseUrl}/${id}`);
  }

  create(obj: ClientPreferenceModel): Observable<ClientPreferenceModel> {
    return this.api.post<ClientPreferenceModel>(this.baseUrl, obj);
  }

  update(id: number, obj: ClientPreferenceModel): Observable<ClientPreferenceModel> {
    return this.api.put<ClientPreferenceModel>(`${this.baseUrl}/${id}`, obj);
  }

  delete(id: number): Observable<void> {
    return this.api.delete<void>(`${this.baseUrl}/${id}`);
  }

  searchByClient(clientId: number): Observable<ClientPreferenceModel[]> {
    return this.api.get<ClientPreferenceModel[]>(`${this.baseUrl}/searchByClient/${clientId}`);
  }

}