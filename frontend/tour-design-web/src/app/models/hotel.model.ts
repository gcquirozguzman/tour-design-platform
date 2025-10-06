// src/app/models/hotel.model.ts
import { PackageHotelModel } from './package-hotel.model';

export interface HotelModel {
  id: number;
  name: string;
  address?: string;
  stars?: number;
  pricePerNight?: number;
  roomType?: string;
  capacity?: number;
  services?: string;
  location?: string;

  packages?: PackageHotelModel[];
}
