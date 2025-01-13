package tn.projet.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.projet.spring.entity.Gymnasium;

@Repository
public interface GymnasiumRepository extends JpaRepository<Gymnasium, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
}
