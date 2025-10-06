import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { PackageHotelModel } from '../models/package-hotel.model';
import { Observable } from 'rxjs';
import { RecommendationResponse } from '../models/recommendation-response.model';
import { RecommendationRequest } from '../models/recommendation-request.model';

@Injectable({
  providedIn: 'root'
})
export class PackageRecommendationService {
  private baseUrl = '/packages-recommendation';

  constructor(private api: ApiService) {}

  recommend(obj: RecommendationRequest): Observable<RecommendationResponse> {
    return this.api.post<RecommendationResponse>(`${this.baseUrl}/recommend`, obj);
  }

}