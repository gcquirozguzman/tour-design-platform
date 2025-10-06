import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { TravelPackageModel } from '../models/travel-package.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TravelPackageService {
  private baseUrl = '/travel-package';

  constructor(private api: ApiService) {}

  list(): Observable<TravelPackageModel[]> {
    return this.api.get<TravelPackageModel[]>(this.baseUrl);
  }

  search(id: number): Observable<TravelPackageModel> {
    return this.api.get<TravelPackageModel>(`${this.baseUrl}/${id}`);
  }

  create(obj: TravelPackageModel): Observable<TravelPackageModel> {
    return this.api.post<TravelPackageModel>(this.baseUrl, obj);
  }

  update(id: number, obj: TravelPackageModel): Observable<TravelPackageModel> {
    return this.api.put<TravelPackageModel>(`${this.baseUrl}/${id}`, obj);
  }

  delete(id: number): Observable<void> {
    return this.api.delete<void>(`${this.baseUrl}/${id}`);
  }
}
