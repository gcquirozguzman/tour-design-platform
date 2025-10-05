package com.tourdesign.platform.controller;

import com.tourdesign.platform.model.RecommendationHistoryModel;
import com.tourdesign.platform.service.RecommendationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recommendation-history")
public class RecommendationHistoryController {

    @Autowired
    private RecommendationHistoryService service;

    @GetMapping
    public ResponseEntity<List<RecommendationHistoryModel>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecommendationHistoryModel> search(@PathVariable Long id) {
        Optional<RecommendationHistoryModel> obj = service.search(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RecommendationHistoryModel> create(@RequestBody RecommendationHistoryModel obj) {
        return ResponseEntity.ok(service.create(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecommendationHistoryModel> update(@PathVariable Long id, @RequestBody RecommendationHistoryModel obj) {
        Optional<RecommendationHistoryModel> updated = service.update(id, obj);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
