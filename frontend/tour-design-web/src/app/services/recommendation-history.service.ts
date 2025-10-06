import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { RecommendationHistoryModel } from '../models/recommendation-history.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecommendationHistoryService {
  private baseUrl = '/recommendation-history';

  constructor(private api: ApiService) {}

  list(): Observable<RecommendationHistoryModel[]> {
    return this.api.get<RecommendationHistoryModel[]>(this.baseUrl);
  }

  search(id: number): Observable<RecommendationHistoryModel> {
    return this.api.get<RecommendationHistoryModel>(`${this.baseUrl}/${id}`);
  }

  create(obj: RecommendationHistoryModel): Observable<RecommendationHistoryModel> {
    return this.api.post<RecommendationHistoryModel>(this.baseUrl, obj);
  }

  update(id: number, obj: RecommendationHistoryModel): Observable<RecommendationHistoryModel> {
    return this.api.put<RecommendationHistoryModel>(`${this.baseUrl}/${id}`, obj);
  }

  delete(id: number): Observable<void> {
    return this.api.delete<void>(`${this.baseUrl}/${id}`);
  }

  searchByClient(clientId: number): Observable<RecommendationHistoryModel[]> {
    return this.api.get<RecommendationHistoryModel[]>(`${this.baseUrl}/searchByClient/${clientId}`);
  }
}
