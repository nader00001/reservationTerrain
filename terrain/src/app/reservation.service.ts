import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ReservationService {
  deleteReservation(id: number) {
    throw new Error('Method not implemented.');
  }
  private apiUrlGymnasium = 'http://localhost:8080/reservations/gymnasiums'; // URL pour les réservations de gymnases
  private apiUrlTerrain = 'http://localhost:8080/reservations/terrains'; // URL pour les réservations de terrains

  constructor(private http: HttpClient) {}

  // Récupérer les réservations de gymnases
  getGymnasiumReservations(): Observable<any> {
    return this.http.get(`${this.apiUrlGymnasium}`);
  }

  // Supprimer une réservation de gymnase
  deleteGymnasiumReservation(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrlGymnasium}/${id}`);
  }

  // Récupérer les réservations de terrains
  getTerrainReservations(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrlTerrain); // Assurez-vous que cette URL est correcte.
  }


  // Supprimer une réservation de terrain
  deleteTerrainReservation(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrlTerrain}/${id}`);
  }
}
