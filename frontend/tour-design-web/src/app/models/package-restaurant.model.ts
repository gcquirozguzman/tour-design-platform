// src/app/models/package-restaurant.model.ts
import { TravelPackageModel } from './travel-package.model';
import { RestaurantModel } from './restaurant.model';

export interface PackageRestaurantModel {
  id: number;
  orderIndex?: number;
  travelPackage?: TravelPackageModel;
  restaurant?: RestaurantModel;
}
