package tn.projet.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.projet.spring.entity.Terrain;

import java.util.Optional;

@Repository
public interface TerrainRepository extends JpaRepository<Terrain, Long> {
    Optional<Terrain> findByName(String name);

    Optional<Terrain> findById(Long aLong);
}
