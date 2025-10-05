package com.tourdesign.platform.controller;

import com.tourdesign.platform.model.PackageHotelModel;
import com.tourdesign.platform.service.PackageHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/package-hotel")
public class PackageHotelController {

    @Autowired
    private PackageHotelService service;

    @GetMapping
    public ResponseEntity<List<PackageHotelModel>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageHotelModel> search(@PathVariable Long id) {
        Optional<PackageHotelModel> obj = service.search(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PackageHotelModel> create(@RequestBody PackageHotelModel obj) {
        return ResponseEntity.ok(service.create(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackageHotelModel> update(@PathVariable Long id, @RequestBody PackageHotelModel obj) {
        Optional<PackageHotelModel> updated = service.update(id, obj);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
