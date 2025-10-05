package com.tourdesign.platform.controller;

import com.tourdesign.platform.dto.request.RecommendationRequest;
import com.tourdesign.platform.dto.response.RecommendationResponse;
import com.tourdesign.platform.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/packages")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PackageRecommendationController {

    private final RecommendationService recommendationService;

    @PostMapping("/recommend")
    public ResponseEntity<RecommendationResponse> recommend(@RequestBody RecommendationRequest request) {
        RecommendationResponse response = recommendationService.createRecommendation(request);
        return ResponseEntity.ok(response);
    }

}
