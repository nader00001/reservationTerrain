import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { jsPDF } from 'jspdf'; // Pour la génération de PDF

// Interface de la réponse de l'API
interface ApiResponse {
  success: boolean;
  message: string;
  data?: any;
}

@Component({
  selector: 'app-gymnasiumreservation',
  templateUrl: './gymnasiumreservation.component.html',
  styleUrls: ['./gymnasiumreservation.component.css'],
  standalone: true,
  imports: [CommonModule, FormsModule],
})
export class GymnasiumreservationComponent implements OnInit {
  reservation = {
    gymnasium: { id: null },
    date: '',
    time: '',
    endDate: '',
    startDate: '',
    reservedBy: '',
    endTime: '',
  };

  gymnasiums: { id: number; name: string }[] = [];
  message: string = ''; // Message de succès
  error="";   // Message d'erreur

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadGymnasiums();
  }

  // Charger la liste des gymnases depuis l'API
  loadGymnasiums() {
    this.http.get<{ id: number; name: string }[]>('http://localhost:8080/api/gymnasiums').subscribe(
      (response) => {
        this.gymnasiums = response;
      },
      (error) => {
        this.error = 'Erreur de récupération des gymnases : ' + (error.message || 'Inconnue');
        console.error('Erreur:', error);
      }
    );
  }

  // Calculer la date/heure de début et de fin avec ajustement automatique
  calculateEndDateTime() {
    if (!this.reservation.date || !this.reservation.time || !this.reservation.endTime) {
      this.error = 'Date ou heure invalide.';
      return false;
    }

    const startDateTime = new Date(`${this.reservation.date}T${this.reservation.time}:00`);
    let endDateTime = new Date(`${this.reservation.date}T${this.reservation.endTime}:00`);

    // Si l'heure de fin est incorrecte ou trop proche de l'heure de début, ajuster automatiquement
    if (startDateTime >= endDateTime) {
      endDateTime = new Date(startDateTime.getTime() + 60 * 60 * 1000); // Ajouter 1 heure
    }

    this.reservation.startDate = startDateTime.toISOString();
    this.reservation.endDate = endDateTime.toISOString();
    this.error = ''; // Réinitialiser les erreurs
    return true;
  }

  // Générer un fichier PDF avec les informations de réservation
  generatePDF() {
    const doc = new jsPDF();
    doc.text('Confirmation de réservation - Gymnase', 20, 10);
    doc.text(`Réservé par : ${this.reservation.reservedBy}`, 20, 30);
    doc.text(`Date de début : ${this.reservation.startDate}`, 20, 40);
    doc.text(`Date de fin : ${this.reservation.endDate}`, 20, 50);

    doc.save('reservation_gymnasium.pdf');
  }

  // Soumettre la réservation
  onSubmit() {
    if (!this.calculateEndDateTime()) return;

    const selectedGymnasium = this.gymnasiums.find(g => g.id === this.reservation.gymnasium.id);
    const reservationToSend = {
      gymnasium: { id: this.reservation.gymnasium.id },
      reservedBy: this.reservation.reservedBy,
      startDate: this.reservation.startDate,
      endDate: this.reservation.endDate,
    };

    console.log('Données envoyées au backend:', reservationToSend);

    this.http.post<ApiResponse>('http://localhost:8080/reservations/reserve/gymnasium', reservationToSend)
      .subscribe({
        next: () =>  {
          console.log('Réponse du serveur:') ;
          this.message = "Réservation réussie pour le gymnase ";
        },
        error: (error) => {

          if(error.status==400 && error.error=="Le gymnase n'est pas disponible pour les dates spécifiées."){
            this.error="Le gymnase n'est pas disponible pour les dates spécifiées";
      }
    }})}





    }

