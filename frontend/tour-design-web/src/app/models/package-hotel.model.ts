// src/app/models/package-hotel.model.ts
import { TravelPackageModel } from './travel-package.model';
import { HotelModel } from './hotel.model';

export interface PackageHotelModel {
  id: number;
  orderIndex?: number;
  travelPackage?: TravelPackageModel;
  hotel?: HotelModel;
}
