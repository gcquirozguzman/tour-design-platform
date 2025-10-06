import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { TouristSpotModel } from '../models/tourist-spot.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TouristSpotService {
  private baseUrl = '/tourist-spot';

  constructor(private api: ApiService) {}

  list(): Observable<TouristSpotModel[]> {
    return this.api.get<TouristSpotModel[]>(this.baseUrl);
  }

  search(id: number): Observable<TouristSpotModel> {
    return this.api.get<TouristSpotModel>(`${this.baseUrl}/${id}`);
  }

  create(obj: TouristSpotModel): Observable<TouristSpotModel> {
    return this.api.post<TouristSpotModel>(this.baseUrl, obj);
  }

  update(id: number, obj: TouristSpotModel): Observable<TouristSpotModel> {
    return this.api.put<TouristSpotModel>(`${this.baseUrl}/${id}`, obj);
  }

  delete(id: number): Observable<void> {
    return this.api.delete<void>(`${this.baseUrl}/${id}`);
  }
}
