import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { RestaurantModel } from '../models/restaurant.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {
  private baseUrl = '/restaurant';

  constructor(private api: ApiService) {}

  list(): Observable<RestaurantModel[]> {
    return this.api.get<RestaurantModel[]>(this.baseUrl);
  }

  search(id: number): Observable<RestaurantModel> {
    return this.api.get<RestaurantModel>(`${this.baseUrl}/${id}`);
  }

  create(obj: RestaurantModel): Observable<RestaurantModel> {
    return this.api.post<RestaurantModel>(this.baseUrl, obj);
  }

  update(id: number, obj: RestaurantModel): Observable<RestaurantModel> {
    return this.api.put<RestaurantModel>(`${this.baseUrl}/${id}`, obj);
  }

  delete(id: number): Observable<void> {
    return this.api.delete<void>(`${this.baseUrl}/${id}`);
  }
}
