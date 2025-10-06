// src/app/models/tourist-spot.model.ts
import { PackageSpotModel } from './package-spot.model';

export interface TouristSpotModel {
  id: number;
  name: string;
  spotType?: string;
  location?: string;
  entranceFee?: number;
  difficultyLevel?: number;
  restrictions?: string;

  packages?: PackageSpotModel[];
}
