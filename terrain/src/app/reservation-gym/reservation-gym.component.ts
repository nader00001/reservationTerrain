import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../reservation.service';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-liste-reservation-gym',
  standalone: true,
  imports: [CommonModule, HttpClientModule, RouterModule],
  templateUrl: './reservation-gym.component.html',
  styleUrls: ['./reservation-gym.component.css'],
})
export class ListeReservationgymComponent implements OnInit {
  gymnasiumReservations: any[] = [];
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private reservationService: ReservationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadReservations();
  }

  loadReservations(): void {
    this.reservationService.getGymnasiumReservations().subscribe(
      (data) => {
        console.log('Données récupérées :', data);
        this.gymnasiumReservations = Array.isArray(data) ? data : [];
      },
      (error) => {
        console.error('Erreur lors de la récupération des réservations :', error);
        this.errorMessage =
          'Erreur lors de la récupération des réservations : ' + error.message;
        setTimeout(() => {
          this.errorMessage = '';
        }, 3000);
      }
    );
  }

  // Méthode de suppression
  deleteReservation(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette réservation?')) {
      this.reservationService.deleteGymnasiumReservation(id).subscribe(
        (response) => {
          this.successMessage = 'Réservation supprimée avec succès';
          setTimeout(() => {
            this.successMessage = '';
          }, 3000);
          this.loadReservations();  // Recharger les réservations après suppression
        },
        (error) => {
          console.error('Erreur lors de la suppression de la réservation:', error);
          this.errorMessage = 'Erreur lors de la suppression de la réservation';
          setTimeout(() => {
            this.errorMessage = '';
          }, 3000);
        }
      );
    }
  }
}
