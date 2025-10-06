// src/app/models/recommendation-response.model.ts
import { ClientModel } from './client.model';
import { ClientPreferenceModel } from './client-preference.model';
import { TravelPackageModel } from './travel-package.model';
import { HotelModel } from './hotel.model';
import { RestaurantModel } from './restaurant.model';
import { TouristSpotModel } from './tourist-spot.model';
import { RecommendationHistoryModel } from './recommendation-history.model';

export interface RecommendationResponse {
  client: ClientModel;
  preferences: ClientPreferenceModel[];
  travelPackage: TravelPackageModel;
  hotel: HotelModel;
  restaurant: RestaurantModel;
  spots: TouristSpotModel[];
  history: RecommendationHistoryModel;
}
