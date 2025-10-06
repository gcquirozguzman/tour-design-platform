// src/app/models/recommendation-history.model.ts
import { ClientModel } from './client.model';
import { TravelPackageModel } from './travel-package.model';

export interface RecommendationHistoryModel {
  id: number;
  recommendationDate?: string;
  accepted?: boolean;

  client?: ClientModel;
  travelPackage?: TravelPackageModel;
}
