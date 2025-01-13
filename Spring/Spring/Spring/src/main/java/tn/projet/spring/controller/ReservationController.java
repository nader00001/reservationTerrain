package tn.projet.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService; // Correction ici

    // Réserver un terrain
    @PostMapping("/reserve")
    public ResponseEntity<?> reserveSpace(@RequestBody Reservation reservation) {
        try {
            reservationService.createReservation(reservation);
            return ResponseEntity.ok("Réservation confirmée !");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur interne du serveur.");
        }
    }

    // Obtenir les réservations disponibles pour un terrain donné
    @GetMapping("/available")
    public ResponseEntity<?> getAvailableReservations(@RequestParam String space) {
        try {
            List<Reservation> availableReservations = reservationService.getReservationsForSpace(space);
            return ResponseEntity.ok(availableReservations);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Terrain introuvable");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur interne du serveur.");
        }
    }
}
