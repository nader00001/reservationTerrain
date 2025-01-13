import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../reservation.service';
import { CommonModule } from '@angular/common'; // Ajoutez l'importation de CommonModule

@Component({
  selector: 'app-reservation-terrain',
  standalone: true, // Assurez-vous que le composant est déclaré comme stand-alone
  imports: [CommonModule], // Ajoutez CommonModule à la liste des imports
  templateUrl: './reservation-terrain.component.html',
  styleUrls: ['./reservation-terrain.component.css'],
})
export class ReservationTerrainComponent implements OnInit {
  reservations: any[] = []; // Liste des réservations de terrains
  error: string | null = null; // Message d'erreur
  success: string | null = null; // Message de succès

  constructor(private reservationService: ReservationService) {}

  ngOnInit(): void {
    // Charge les réservations de terrain au chargement du composant
    this.loadReservations();
  }

  loadReservations(): void {
    this.reservationService.getTerrainReservations().subscribe(
      (data) => {
        console.log('Données récupérées :', data);
        try {
          const reservations = Array.isArray(data) ? data : [];
          this.reservations = reservations;
          this.error = null;
        } catch (err) {
          console.error('Erreur lors du traitement des données:', err);
          this.error = 'Erreur de traitement des données.';
        }
      },
      (error) => {
        console.error('Erreur lors du chargement des réservations :', error);
        this.error = 'Erreur lors de la récupération des réservations : ' + error.message;
      }
    );
  }




}
