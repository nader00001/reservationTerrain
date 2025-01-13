import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

// Interface de la réponse de l'API
interface ApiResponse {
  success: boolean;
  message: string;
  data?: any;
}

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule , RouterModule]
})
export class ReservationComponent implements OnInit {
  reservation = {
    terrain: { id: null },
    date: '',
    time: '',
    endDate: '',
    startDate: '',
    reservedBy: '',
    endTime: ''
  };

  terrains: { id: number, name: string }[] = [];
  message: string = ''; // Variable pour afficher le message
  error: string = ''; // Message d'erreur
  isAvailable: boolean = false; // Variable pour indiquer si le terrain est disponible

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    // Récupère les terrains depuis le serveur
    this.http.get<{ id: number, name: string }[]>('http://localhost:8080/api/terrains').subscribe(
      (response) => {
        if (Array.isArray(response)) {
          this.terrains = response;
        } else {
          this.message = 'La réponse du serveur n\'est pas un tableau.';
          console.error('La réponse du serveur est incorrecte', response);
        }
      },
      (error) => {
        this.message = 'Impossible de charger les terrains. Veuillez réessayer plus tard.';
        console.error('Erreur de récupération des terrains', error);
      }
    );
  }

  calculateEndDateTime(): boolean {
    if (!this.reservation.date || !this.reservation.time || !this.reservation.endTime) {
      this.error = 'Veuillez remplir tous les champs de date et d\'heure.';
      return false; // Retourne false si les champs sont invalides
    }

    const startDateTime = `${this.reservation.date}T${this.reservation.time}:00`;
    const startDate = new Date(startDateTime);
    const endDateTime = `${this.reservation.date}T${this.reservation.endTime}:00`;
    const endDate = new Date(endDateTime);

    if (startDate >= endDate) {
      this.error = 'L\'heure de fin ne peut pas être avant l\'heure de début.';
      return false; // Retourne false si l'heure de fin est avant l'heure de début
    }

    this.reservation.startDate = startDate.toISOString();
    this.reservation.endDate = endDate.toISOString();
    return true; // Retourne true si tout est valide
  }

  onSubmit(): void {
    if (!this.calculateEndDateTime()) return;

    const reservationToSend = {
      terrain: { id: this.reservation.terrain.id },
      reservedBy: this.reservation.reservedBy,
      startDate: this.reservation.startDate,
      endDate: this.reservation.endDate,
    };

    this.http.post<ApiResponse>('http://localhost:8080/reservations/reserve/terrain', reservationToSend)
      .subscribe({
        next: (response) => {
          if (response.success) {
            this.message = "Réservation réussie pour le terrain.";
            this.error = ''; // Réinitialisation du message d'erreur
          } else {
            this.error = response.message || "Une erreur est survenue lors de la réservation.";
          }
        },
        error: (error) => {
          if (error.status === 409 && error.error) {
            this.error = error.error; // Récupérer le message précis du serveur
          } else {
            this.error = 'Erreur lors de la soumission de la réservation.';
          }
        },
      });
  }
}
