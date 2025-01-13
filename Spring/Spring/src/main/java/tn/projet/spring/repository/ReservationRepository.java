package tn.projet.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.projet.spring.entity.Gymnasium;
import tn.projet.spring.entity.Reservation;
import tn.projet.spring.entity.Terrain;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Trouver les réservations en conflit pour un terrain dans une période spécifique
    List<Reservation> findByTerrainAndStartDateBeforeAndEndDateAfter(
            Terrain terrain,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    // Trouver les réservations en conflit pour un gymnase dans une période spécifique
    List<Reservation> findByGymnasiumAndStartDateBeforeAndEndDateAfter(
            Gymnasium gymnasium,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    // Trouver toutes les réservations pour un terrain donné
    List<Reservation> findByTerrain(Terrain terrain);

    // Trouver toutes les réservations pour un gymnase donné
    List<Reservation> findByGymnasium(Gymnasium gymnasium);

    // Trouver toutes les réservations ayant un terrain non null
    List<Reservation> findAllByTerrainIsNotNull();

    // Trouver toutes les réservations ayant un gymnase non null
    List<Reservation> findAllByGymnasiumIsNotNull();

    // Vérifier s'il existe des réservations pour un terrain dans une période donnée
    boolean existsByTerrainAndStartDateBeforeAndEndDateAfter(
            Terrain terrain,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    // Vérifier s'il existe des conflits de réservation pour un gymnase
    boolean existsByGymnasiumAndStartDateBeforeAndEndDateAfter(
            Gymnasium gymnasium,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    // Trouver les réservations pour un terrain avec une période spécifique
    List<Reservation> findByTerrain_IdAndStartDateBeforeAndEndDateAfter(
            Long terrainId, LocalDateTime startDate, LocalDateTime endDate);

    // Vérifier les conflits de réservation de terrain (période inverse)
    List<Reservation> findByTerrainAndStartDateLessThanAndEndDateGreaterThan(
            Terrain terrain, LocalDateTime endDate, LocalDateTime startDate
    );

    List<Reservation> findByTerrainIsNotNull();

    List<Reservation> findByGymnasiumIsNotNull();

    List<Reservation> findByGymnasiumAndStartDateLessThanAndEndDateGreaterThan(Gymnasium gymnasium, LocalDateTime endDate, LocalDateTime startDate);
    @Query("SELECT r FROM Reservation r WHERE r.gymnasium.id = :gymnasiumId")
    List<Reservation> findByGymnasiumId(@Param("gymnasiumId") Long gymnasiumId);
    @Query("SELECT r FROM Reservation r WHERE r.terrain.id = :terrainId")
    List<Reservation> findByTerrainId(@Param("terrainId") Long terrainId);

}