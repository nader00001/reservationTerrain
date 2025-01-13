package tn.projet.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.projet.spring.entity.Terrain;
import tn.projet.spring.repository.TerrainRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TerrainRepository terrainRepository;

    // Vérifier la disponibilité du terrain
    public boolean isTerrainAvailable(Long terrainId, LocalDateTime startDate, LocalDateTime endDate) {
        Terrain terrain = terrainRepository.findById(terrainId).orElse(null);
        if (terrain == null) {
            return false; // Terrain introuvable
        }
        List<Reservation> conflictingReservations = reservationRepository.findByTerrainAndStartDateLessThanAndEndDateGreaterThan(
                terrain, endDate, startDate);
        return ((List<?>) conflictingReservations).isEmpty(); // Aucune réservation ne chevauche cette période
    }

    // Créer une réservation
    public Reservation createReservation(Reservation reservation) {
        if (!isTerrainAvailable(
                reservation.getTerrain().getId(),
                reservation.getStartDate(),
                reservation.getEndDate())) {
            throw new IllegalStateException("Le terrain n'est pas disponible pour les dates spécifiées.");
        }
        return reservationRepository.save(reservation);
    }

    // Obtenir toutes les réservations pour un terrain donné
    public List<Reservation> getReservationsForSpace(String space) {
        Terrain terrain = terrainRepository.findByName(space);
        if (terrain == null) {
            throw new IllegalArgumentException("Terrain introuvable");
        }
        return reservationRepository.findByTerrain(terrain);
    }
}
