package com.tourdesign.platform.controller;

import com.tourdesign.platform.model.ClientModel;
import com.tourdesign.platform.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<ClientModel>> list() {
        List<ClientModel> list = service.list();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientModel> search(@PathVariable Long id) {
        Optional<ClientModel> obj = service.search(id);
        return obj.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/searchByQuery")
    public ResponseEntity<List<ClientModel>> searchByQuery(@RequestParam String query) {
        List<ClientModel> results = service.searchByNameOrLastname(query);
        return ResponseEntity.ok(results);
    }

    @PostMapping
    public ResponseEntity<ClientModel> create(@RequestBody ClientModel obj) {
        ClientModel createdModel = service.create(obj);
        return ResponseEntity.ok(createdModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientModel> update(@PathVariable Long id, @RequestBody ClientModel obj) {
        Optional<ClientModel> updatedUser = service.update(id, obj);
        return updatedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}