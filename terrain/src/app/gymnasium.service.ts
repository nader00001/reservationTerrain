// gymnasium.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

export interface Gymnasium {
  capacity: any;
  id: number;
  name: string;
  location: string;
  gym_count?: number;  // Occupation actuelle du terrain
}


@Injectable({
  providedIn: 'root',
})
export class GymnasiumService {
  private apiUrl = 'http://localhost:8080/api/gymnasiums'; // L'URL de votre API

  constructor(private http: HttpClient) {}

  // Récupérer un gymnase par ID
  getGymnasiumById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  updateGymnasium(id: number, gymnasium: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, gymnasium);
  }

  // Récupérer tous les gymnases
  getAllGymnasiums(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  deleteGymnasium(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      catchError((error) => {
        console.error('Erreur lors de la suppression :', error);
        return throwError(error); // Rethrow the error for further handling
      })
    );
  }

  // Récupérer les données du capteur pour un terrain
  getSensorData(id: number): Observable<string> {
    const url = `${this.apiUrl}/getSensorData/${id}`;
    return this.http.get<string>(url);
  }

}
