import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { HotelModel } from '../models/hotel.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HotelService {
  private baseUrl = '/hotel';

  constructor(private api: ApiService) {}

  list(): Observable<HotelModel[]> {
    return this.api.get<HotelModel[]>(this.baseUrl);
  }

  search(id: number): Observable<HotelModel> {
    return this.api.get<HotelModel>(`${this.baseUrl}/${id}`);
  }

  create(obj: HotelModel): Observable<HotelModel> {
    return this.api.post<HotelModel>(this.baseUrl, obj);
  }

  update(id: number, obj: HotelModel): Observable<HotelModel> {
    return this.api.put<HotelModel>(`${this.baseUrl}/${id}`, obj);
  }

  delete(id: number): Observable<void> {
    return this.api.delete<void>(`${this.baseUrl}/${id}`);
  }
}