// src/app/models/client.model.ts
import { ClientPreferenceModel } from './client-preference.model';
import { RecommendationHistoryModel } from './recommendation-history.model';

export interface ClientModel {
  id: number;
  firstName: string;
  lastName: string;
  birthDate?: string;
  gender?: string;
  nationality?: string;
  email?: string;
  phone?: string;
  numberOfChildren?: number;
  occupation?: string;
  annualIncome?: number;
  languages?: string;
  registrationDate?: string;
  active?: boolean;

  preferences?: ClientPreferenceModel[];
  recommendationHistory?: RecommendationHistoryModel[];
}
