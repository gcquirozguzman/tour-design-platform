package com.tourdesign.platform.dto.response;

import com.tourdesign.platform.model.*;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class RecommendationResponse {
    private ClientModel client;
    private List<ClientPreferenceModel> preferences;
    private TravelPackageModel travelPackage;
    private HotelModel hotel;
    private RestaurantModel restaurant;
    private List<TouristSpotModel> spots;
    private RecommendationHistoryModel history;
}