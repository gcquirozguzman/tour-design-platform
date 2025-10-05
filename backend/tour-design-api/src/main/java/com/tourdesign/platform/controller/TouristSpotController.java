package com.tourdesign.platform.controller;

import com.tourdesign.platform.model.TouristSpotModel;
import com.tourdesign.platform.service.TouristSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tourist-spot")
public class TouristSpotController {

    @Autowired
    private TouristSpotService service;

    @GetMapping
    public ResponseEntity<List<TouristSpotModel>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TouristSpotModel> search(@PathVariable Long id) {
        Optional<TouristSpotModel> obj = service.search(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TouristSpotModel> create(@RequestBody TouristSpotModel obj) {
        return ResponseEntity.ok(service.create(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TouristSpotModel> update(@PathVariable Long id, @RequestBody TouristSpotModel obj) {
        Optional<TouristSpotModel> updated = service.update(id, obj);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
