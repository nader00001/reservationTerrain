package tn.projet.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.projet.spring.entity.Terrain;
import tn.projet.spring.repository.TerrainRepository;

import java.util.List;

@Service
public class TerrainService {

    @Autowired
    private TerrainRepository terrainRepository;

    // Créer un terrain
    public Terrain createTerrain(Terrain terrain) {
        return terrainRepository.save(terrain);
    }

    // Lire tous les terrains
    public List<Terrain> getAllTerrains() {
        return terrainRepository.findAll();
    }

    // Lire un terrain par ID
    public Terrain getTerrainById(Long id) {
        return terrainRepository.findById(id).orElse(null);
    }

    // Mettre à jour un terrain
    public Terrain updateTerrain(Long id, Terrain updatedTerrain) {
        Terrain terrain = terrainRepository.findById(id).orElse(null);
        if (terrain != null) {
            terrain.setName(updatedTerrain.getName());
            terrain.setLocation(updatedTerrain.getLocation());
            terrain.setCapacity(updatedTerrain.getCapacity());
            terrain.setType(updatedTerrain.getType());
            return terrainRepository.save(terrain);
        }
        return null;
    }

    // Supprimer un terrain
    public void deleteTerrain(Long id) {
        terrainRepository.deleteById(id);
    }


}
