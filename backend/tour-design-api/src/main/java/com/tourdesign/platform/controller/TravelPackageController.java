package com.tourdesign.platform.controller;

import com.tourdesign.platform.model.TravelPackageModel;
import com.tourdesign.platform.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/travel-package")
public class TravelPackageController {

    @Autowired
    private TravelPackageService service;

    @GetMapping
    public ResponseEntity<List<TravelPackageModel>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelPackageModel> search(@PathVariable Long id) {
        Optional<TravelPackageModel> obj = service.search(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TravelPackageModel> create(@RequestBody TravelPackageModel obj) {
        return ResponseEntity.ok(service.create(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TravelPackageModel> update(@PathVariable Long id, @RequestBody TravelPackageModel obj) {
        Optional<TravelPackageModel> updated = service.update(id, obj);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
