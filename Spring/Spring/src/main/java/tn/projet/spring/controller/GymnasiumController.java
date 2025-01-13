package tn.projet.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.projet.spring.entity.Gymnasium;
import tn.projet.spring.service.GymnasiumService;

import java.util.List;

@RestController
@RequestMapping("/api/gymnasiums")
public class GymnasiumController {

    @Autowired
    private GymnasiumService gymnasiumService;

    // Créer une salle
    @PostMapping
    public ResponseEntity<Gymnasium> createGymnasium(@RequestBody Gymnasium gymnasium) {
        Gymnasium createdGymnasium = gymnasiumService.createGymnasium(gymnasium);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGymnasium);
    }

    @GetMapping
    public List<Gymnasium> getAllGymnasiums() {
        return gymnasiumService.getAllGymnasiums();
    }

    // Lire une salle par ID
    @GetMapping("/{id}")
    public ResponseEntity<Gymnasium> getGymnasiumById(@PathVariable Long id) {
        Gymnasium gymnasium = gymnasiumService.getGymnasiumById(id);
        return ResponseEntity.ok(gymnasium);
    }

    // Mettre à jour une salle
    @PutMapping("/{id}")
    public ResponseEntity<Gymnasium> updateGymnasium(@PathVariable Long id, @RequestBody Gymnasium updatedGymnasium) {
        System.out.println("ID: " + id);
        System.out.println("Gymnasium: " + updatedGymnasium);
        Gymnasium gymnasium = gymnasiumService.updateGymnasium(id, updatedGymnasium);
        return ResponseEntity.ok(gymnasium);
    }

    // Supprimer une salle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGymnasium(@PathVariable Long id) {
        gymnasiumService.deleteGymnasium(id);
        return ResponseEntity.noContent().build();
    }


    // Récupérer les données du capteur pour un terrain spécifique
    @GetMapping("/getSensorData/{id}")
    public ResponseEntity<String> getSensorData(@PathVariable Long id) {
        String sensorData = gymnasiumService.getSensorData(id);
        if (sensorData != null) {
            return ResponseEntity.ok(sensorData);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Terrain non trouvé");
    }
}
