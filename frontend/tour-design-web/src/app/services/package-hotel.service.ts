import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { PackageHotelModel } from '../models/package-hotel.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PackageHotelService {
  private baseUrl = '/package-hotel';

  constructor(private api: ApiService) {}

  list(): Observable<PackageHotelModel[]> {
    return this.api.get<PackageHotelModel[]>(this.baseUrl);
  }

  search(id: number): Observable<PackageHotelModel> {
    return this.api.get<PackageHotelModel>(`${this.baseUrl}/${id}`);
  }

  create(obj: PackageHotelModel): Observable<PackageHotelModel> {
    return this.api.post<PackageHotelModel>(this.baseUrl, obj);
  }

  update(id: number, obj: PackageHotelModel): Observable<PackageHotelModel> {
    return this.api.put<PackageHotelModel>(`${this.baseUrl}/${id}`, obj);
  }

  delete(id: number): Observable<void> {
    return this.api.delete<void>(`${this.baseUrl}/${id}`);
  }
}