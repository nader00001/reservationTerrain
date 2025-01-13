package tn.projet.spring.service;

import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.projet.spring.entity.Reservation;
import tn.projet.spring.entity.Gymnasium;
import tn.projet.spring.entity.Terrain;
import tn.projet.spring.repository.GymnasiumRepository;
import tn.projet.spring.repository.ReservationRepository;
import tn.projet.spring.repository.TerrainRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TerrainRepository terrainRepository;

    @Autowired
    private GymnasiumRepository gymnasiumRepository;
    private MessageUtil log;

    // Vérifier la disponibilité du terrain
    public boolean isTerrainAvailable(Long terrainId, LocalDateTime startDate, LocalDateTime endDate) {
        // Vérifier si le terrain existe
        Optional<Terrain> terrainOpt = terrainRepository.findById(terrainId);
        if (terrainOpt.isEmpty()) {
            return false; // Terrain introuvable, retour à false
        }

        // Récupérer les réservations existantes pour ce terrain
        List<Reservation> existingReservations = reservationRepository.findByTerrainAndStartDateBeforeAndEndDateAfter(
                terrainOpt.get(), endDate, startDate);

        // Si des réservations conflictuelles sont trouvées, le terrain n'est pas disponible
        if (!existingReservations.isEmpty()) {
            return false; // Conflit détecté
        }

        return true; // Aucun conflit, terrain disponible
    }

    public Reservation createTerrainReservation(Reservation reservation) {
        if (reservation.getTerrain() == null || reservation.getTerrain().getId() == null) {
            throw new IllegalArgumentException("ID du terrain est manquant.");
        }

        // Vérification si le terrain existe
        Optional<Terrain> terrainOpt = terrainRepository.findById(reservation.getTerrain().getId());
        if (terrainOpt.isEmpty()) {
            throw new IllegalArgumentException("Le terrain spécifié n'existe pas.");
        }

        // Vérifier la disponibilité du terrain
        if (!isTerrainAvailable(
                reservation.getTerrain().getId(),
                reservation.getStartDate(),
                reservation.getEndDate())) {
            throw new IllegalStateException("Le terrain n'est pas disponible pour les dates spécifiées.");
        }

        // Associer le terrain trouvé à la réservation
        reservation.setTerrain(terrainOpt.get());

        // Enregistrer la réservation
        return reservationRepository.save(reservation);
    }



    // Vérifier la disponibilité du gymnase
    public boolean isGymnasiumAvailable(Long gymnasiumId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Reservation> existingReservations = reservationRepository.findByGymnasiumId(gymnasiumId);
        if (existingReservations == null || existingReservations.isEmpty()) {
            return true; // Aucune réservation existante, gymnase disponible
        }

        // Vérifier les chevauchements
        for (Reservation existing : existingReservations) {
            if (existing.getStartDate().isBefore(endDate) && startDate.isBefore(existing.getEndDate())) {
                return false; // Conflit détecté
            }
        }
        return true; // Pas de conflit
    }



    public Reservation createGymnasiumReservation(Reservation reservation) {
        if (reservation.getGymnasium() == null || reservation.getGymnasium().getId() == null) {
            throw new IllegalArgumentException("ID du gymnase est manquant.");
        }

        // Vérification si le gymnase existe
        Optional<Gymnasium> gymnasiumOpt = gymnasiumRepository.findById(reservation.getGymnasium().getId());
        if (gymnasiumOpt.isEmpty()) {
            throw new IllegalArgumentException("Le gymnase spécifié n'existe pas.");
        }

        // Vérifier la disponibilité
        if (!isGymnasiumAvailable(
                reservation.getGymnasium().getId(),
                reservation.getStartDate(),
                reservation.getEndDate())) {
            throw new IllegalStateException("Le gymnase n'est pas disponible pour les dates spécifiées.");
        }

        return reservationRepository.save(reservation);
    }

    // Supprimer une réservation de terrain par ID
    public void deleteTerrainReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    // Supprimer une réservation de gymnase par ID
    public void deleteGymnasiumReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    // Obtenir toutes les réservations pour un terrain
    public List<Reservation> getAllGymnasiumReservations() {
        return reservationRepository.findAllByGymnasiumIsNotNull(); // Doit renvoyer les réservations où gymnase != null
    }


    public List<Reservation> getAllTerrainReservations() {
        return reservationRepository.findAll(); // Doit renvoyer les réservations où terrain != null
    }

    // Obtenir toutes les réservations d'un terrain spécifique
    public List<Reservation> getReservationsForSpace(String space) {
        Optional<Terrain> terrain = terrainRepository.findByName(space);
        if (terrain.isEmpty()) {
            throw new IllegalArgumentException("Terrain introuvable");
        }
        return reservationRepository.findByTerrain(terrain.get());
    }



}
