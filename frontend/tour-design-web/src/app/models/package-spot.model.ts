// src/app/models/package-spot.model.ts
import { TravelPackageModel } from './travel-package.model';
import { TouristSpotModel } from './tourist-spot.model';

export interface PackageSpotModel {
  id: number;
  orderIndex?: number;
  travelPackage?: TravelPackageModel;
  touristSpot?: TouristSpotModel;
}
