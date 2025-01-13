package tn.projet.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.projet.spring.entity.Terrain;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Récupérer toutes les réservations pour un terrain donné qui chevauchent une période spécifique
    List<Reservation> findByTerrainAndStartDateLessThanAndEndDateGreaterThan(Terrain terrain, LocalDateTime endDate, LocalDateTime startDate);

    List<Reservation> findByTerrain(Terrain terrain);
}
