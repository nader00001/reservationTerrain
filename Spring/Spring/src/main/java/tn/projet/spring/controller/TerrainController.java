package tn.projet.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.projet.spring.entity.Gymnasium;
import tn.projet.spring.entity.Terrain;
import tn.projet.spring.repository.TerrainRepository;
import tn.projet.spring.service.GymnasiumService;
import tn.projet.spring.service.TerrainService;

import java.util.List;

@RestController
@RequestMapping("/api/terrains")
public class TerrainController {

    @Autowired
    private TerrainService terrainService;
    private GymnasiumService gymnasiumService;
    private TerrainRepository terrainRepository;

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
        // On s'assure que le terrain existe avant de le supprimer
        terrainService.deleteTerrain(id);
        return ResponseEntity.noContent().build(); // Réponse 204 No Content si la suppression a réussi
    }

    // Mettre à jour les données du capteur Arduino (nombre d'occupants et humidité du sol)
    @PostMapping("/updateSensorData/{id}")
    public ResponseEntity<String> updateSensorData(@PathVariable Long id,
                                                   @RequestParam int terrain_count,
                                                   @RequestParam int soilMoisture,
                                                   @RequestParam int gym_count) {
        Terrain updatedTerrain = terrainService.updateSensorData(id, terrain_count, soilMoisture);
        Gymnasium updateGymnasium = gymnasiumService.updateSensorData(id, gym_count);
        if (updatedTerrain != null || updateGymnasium!=null) {
            return ResponseEntity.ok("Données du capteur mises à jour avec succès");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Terrain non trouvé");
    }

    // Récupérer les données du capteur pour un terrain spécifique
    @GetMapping("/getSensorData/{id}")
    public ResponseEntity<String> getSensorData(@PathVariable Long id) {
        String sensorData = terrainService.getSensorData(id);
        if (sensorData != null) {
            return ResponseEntity.ok(sensorData);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Terrain non trouvé");
    }
}
