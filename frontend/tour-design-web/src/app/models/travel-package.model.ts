// src/app/models/travel-package.model.ts
import { PackageHotelModel } from './package-hotel.model';
import { PackageRestaurantModel } from './package-restaurant.model';
import { PackageSpotModel } from './package-spot.model';

export interface TravelPackageModel {
  id: number;
  name: string;
  description?: string;
  durationDays?: number;
  estimatedPrice?: number;
  focusType?: string;
  difficultyLevel?: number;
  targetAudience?: string;
  serviceLanguage?: string;

  hotels?: PackageHotelModel[];
  restaurants?: PackageRestaurantModel[];
  spots?: PackageSpotModel[];
}
