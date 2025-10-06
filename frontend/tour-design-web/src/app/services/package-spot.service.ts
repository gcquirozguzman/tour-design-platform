import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { PackageSpotModel } from '../models/package-spot.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PackageSpotService {
  private baseUrl = '/package-spot';

  constructor(private api: ApiService) {}

  list(): Observable<PackageSpotModel[]> {
    return this.api.get<PackageSpotModel[]>(this.baseUrl);
  }

  search(id: number): Observable<PackageSpotModel> {
    return this.api.get<PackageSpotModel>(`${this.baseUrl}/${id}`);
  }

  create(obj: PackageSpotModel): Observable<PackageSpotModel> {
    return this.api.post<PackageSpotModel>(this.baseUrl, obj);
  }

  update(id: number, obj: PackageSpotModel): Observable<PackageSpotModel> {
    return this.api.put<PackageSpotModel>(`${this.baseUrl}/${id}`, obj);
  }

  delete(id: number): Observable<void> {
    return this.api.delete<void>(`${this.baseUrl}/${id}`);
  }
}
