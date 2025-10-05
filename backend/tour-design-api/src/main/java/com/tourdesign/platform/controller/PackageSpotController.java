package com.tourdesign.platform.controller;

import com.tourdesign.platform.model.PackageSpotModel;
import com.tourdesign.platform.service.PackageSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/package-spot")
public class PackageSpotController {

    @Autowired
    private PackageSpotService service;

    @GetMapping
    public ResponseEntity<List<PackageSpotModel>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageSpotModel> search(@PathVariable Long id) {
        Optional<PackageSpotModel> obj = service.search(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PackageSpotModel> create(@RequestBody PackageSpotModel obj) {
        return ResponseEntity.ok(service.create(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageSpotModel> update(@PathVariable Long id, @RequestBody PackageSpotModel obj) {
        Optional<PackageSpotModel> updated = service.update(id, obj);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
