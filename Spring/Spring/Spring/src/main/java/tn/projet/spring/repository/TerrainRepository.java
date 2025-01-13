package tn.projet.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.projet.spring.entity.Terrain;

@Repository
public interface TerrainRepository extends JpaRepository<Terrain, Long> {
    Terrain findByName(String space);
}
