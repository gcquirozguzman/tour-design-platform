package com.tourdesign.platform.controller;

import com.tourdesign.platform.model.PackageRestaurantModel;
import com.tourdesign.platform.service.PackageRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/package-restaurant")
public class PackageRestaurantController {

    @Autowired
    private PackageRestaurantService service;

    @GetMapping
    public ResponseEntity<List<PackageRestaurantModel>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageRestaurantModel> search(@PathVariable Long id) {
        Optional<PackageRestaurantModel> obj = service.search(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PackageRestaurantModel> create(@RequestBody PackageRestaurantModel obj) {
        return ResponseEntity.ok(service.create(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageRestaurantModel> update(@PathVariable Long id, @RequestBody PackageRestaurantModel obj) {
        Optional<PackageRestaurantModel> updated = service.update(id, obj);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
