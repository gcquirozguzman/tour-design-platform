// src/app/models/client-preference.model.ts
import { ClientModel } from './client.model';

export interface ClientPreferenceModel {
  id: number;
  client?: ClientModel;
  category?: string;
  interestLevel?: number;
  details?: string;
}
