// src/app/models/restaurant.model.ts
import { PackageRestaurantModel } from './package-restaurant.model';

export interface RestaurantModel {
  id: number;
  name: string;
  cuisineType?: string;
  location?: string;
  averagePrice?: number;
  capacity?: number;
  services?: string;

  packages?: PackageRestaurantModel[];
}
