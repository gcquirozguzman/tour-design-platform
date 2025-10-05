package com.tourdesign.platform.controller;

import com.tourdesign.platform.model.ClientPreferenceModel;
import com.tourdesign.platform.service.ClientPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client-preference")
public class ClientPreferenceController {

    @Autowired
    private ClientPreferenceService service;

    @GetMapping
    public ResponseEntity<List<ClientPreferenceModel>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientPreferenceModel> search(@PathVariable Long id) {
        Optional<ClientPreferenceModel> obj = service.search(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClientPreferenceModel> create(@RequestBody ClientPreferenceModel obj) {
        return ResponseEntity.ok(service.create(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientPreferenceModel> update(@PathVariable Long id, @RequestBody ClientPreferenceModel obj) {
        Optional<ClientPreferenceModel> updated = service.update(id, obj);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
