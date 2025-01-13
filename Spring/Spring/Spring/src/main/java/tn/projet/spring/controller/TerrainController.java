package tn.projet.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.projet.spring.entity.Terrain;
import tn.projet.spring.service.TerrainService;

import java.util.List;

@RestController
@RequestMapping("/api/terrains")
public class TerrainController {

    @Autowired
    private TerrainService terrainService;

    // Créer un terrain
    @PostMapping
    public ResponseEntity<Terrain> createTerrain(@RequestBody Terrain terrain) {
        Terrain createdTerrain = terrainService.createTerrain(terrain);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTerrain);
    }

    // Lire tous les terrains
    @GetMapping
    public List<Terrain> getAllTerrains() {
        return terrainService.getAllTerrains();
    }

    // Lire un terrain par ID
    @GetMapping("/{id}")
    public ResponseEntity<Terrain> getTerrainById(@PathVariable Long id) {
        Terrain terrain = terrainService.getTerrainById(id);
        return ResponseEntity.ok(terrain);
    }

    // Mettre à jour un terrain
    @PutMapping("/{id}")
    public ResponseEntity<Terrain> updateTerrain(@PathVariable Long id, @RequestBody Terrain updatedTerrain) {
        Terrain terrain = terrainService.updateTerrain(id, updatedTerrain);
        if (terrain != null) {
            return ResponseEntity.ok(terrain);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Supprimer un terrain
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerrain(@PathVariable Long id) {
        terrainService.deleteTerrain(id);
        return ResponseEntity.noContent().build();
    }
}
