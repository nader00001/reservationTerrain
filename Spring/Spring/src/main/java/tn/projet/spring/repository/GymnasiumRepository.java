package tn.projet.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.projet.spring.entity.Gymnasium;

@Repository
public interface GymnasiumRepository extends JpaRepository<Gymnasium, Long> {
    Gymnasium findByName(String name); // Rechercher un gymnase par son nom
}
