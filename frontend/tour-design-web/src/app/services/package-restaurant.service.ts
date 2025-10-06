import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { PackageRestaurantModel } from '../models/package-restaurant.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PackageRestaurantService {
  private baseUrl = '/package-restaurant';

  constructor(private api: ApiService) {}

  list(): Observable<PackageRestaurantModel[]> {
    return this.api.get<PackageRestaurantModel[]>(this.baseUrl);
  }

  search(id: number): Observable<PackageRestaurantModel> {
    return this.api.get<PackageRestaurantModel>(`${this.baseUrl}/${id}`);
  }

  create(obj: PackageRestaurantModel): Observable<PackageRestaurantModel> {
    return this.api.post<PackageRestaurantModel>(this.baseUrl, obj);
  }

  update(id: number, obj: PackageRestaurantModel): Observable<PackageRestaurantModel> {
    return this.api.put<PackageRestaurantModel>(`${this.baseUrl}/${id}`, obj);
  }

  delete(id: number): Observable<void> {
    return this.api.delete<void>(`${this.baseUrl}/${id}`);
  }
}