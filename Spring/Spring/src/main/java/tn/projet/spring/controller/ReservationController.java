package tn.projet.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.projet.spring.entity.Reservation;
import tn.projet.spring.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reserve/terrain")
    public ResponseEntity<String> reserveTerrain(@RequestBody Reservation reservation) {
        try {
            // Vérification de la disponibilité du terrain avant d'essayer de le réserver
            boolean isAvailable = reservationService.isTerrainAvailable(
                    reservation.getTerrain().getId(),
                    reservation.getStartDate(),
                    reservation.getEndDate()
            );

            // Si le terrain est disponible, effectuer la réservation
            if (isAvailable) {
                Reservation savedReservation = reservationService.createTerrainReservation(reservation);
                return ResponseEntity.ok("Réservation confirmée pour le terrain. Détails : " + savedReservation.toString());
            } else {
                // Si le terrain n'est pas disponible, envoyer un message d'erreur
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Le terrain n'est pas disponible pour cette période. Veuillez choisir une autre plage horaire.");
            }
        } catch (IllegalArgumentException e) {
            // Paramètres manquants ou invalides
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : Paramètres invalides. Assurez-vous que tous les champs sont corrects.");
        } catch (Exception e) {
            // Erreur générale, serveurs ou autres erreurs internes
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + e.getMessage() + ". Veuillez réessayer plus tard.");
        }
    }






    // Réservation de gymnase
    @PostMapping("/reserve/gymnasium")
    public ResponseEntity<?> reserveGymnasium(@RequestBody Reservation reservation) {
        try {
            // Créer la réservation pour le gymnase
            reservationService.createGymnasiumReservation(reservation);
            return ResponseEntity.ok("Réservation de gymnase confirmée !");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur.");
        }
    }

    // Supprimer une réservation de terrain
    @DeleteMapping("/terrains/{id}")
    public ResponseEntity<?> deleteTerrainReservation(@PathVariable Long id) {
        try {
            reservationService.deleteTerrainReservation(id);
            return ResponseEntity.status(HttpStatus.OK).body("Réservation de terrain supprimée avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Réservation de terrain non trouvée.");
        }
    }

    // Supprimer une réservation de gymnase
    @DeleteMapping("/gymnasiums/{id}")
    public ResponseEntity<?> deleteGymnasiumReservation(@PathVariable Long id) {
        try {
            reservationService.deleteGymnasiumReservation(id);
            return ResponseEntity.status(HttpStatus.OK).body("Réservation de gymnase supprimée avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Réservation de gymnase non trouvée.");
        }
    }

    @GetMapping("/gymnasiums")
    public ResponseEntity<?> getAllGymnasiumReservations() {
        try {
            List<Reservation> reservations = reservationService.getAllGymnasiumReservations();
            if (reservations.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune réservation de gymnase disponible.");
            } else {
                return ResponseEntity.ok(reservations);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur : " + e.getMessage());
        }
    }
/*
    @GetMapping("/terrains")
    public ResponseEntity<?> getAllTerrainReservations() {
        try {
            List<Reservation> reservations = reservationService.getAllTerrainReservations();
            if (reservations.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune réservation de terrain disponible.");
            } else {
                return ResponseEntity.ok(reservations);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur : " + e.getMessage());
        }
    }

*/
@GetMapping("/terrains")
public ResponseEntity<?> getAllTerrainReservations() {

        List<Reservation> reservations = reservationService.getAllTerrainReservations();

    return ResponseEntity.ok(reservations);

}



    // Obtenir les réservations disponibles pour un terrain donné
    @GetMapping("/available")
    public ResponseEntity<?> getAvailableReservations(@RequestParam String space) {
        try {
            List<Reservation> availableReservations = reservationService.getReservationsForSpace(space);
            return ResponseEntity.ok(availableReservations);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur.");
        }
    }
}
