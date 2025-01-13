import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Terrain {
  type: any;
  capacity: any;
  id: number;
  name: string;
  location: string;
  terrain_count?: number;  // Occupation actuelle du terrain
  soil_moisture?: number;      // Humidité du sol
}

@Injectable({
  providedIn: 'root',
})
export class TerrainService {
  private apiUrl = 'http://localhost:8080/api/terrains';

  constructor(private http: HttpClient) {}

  getAllTerrains(): Observable<Terrain[]> {
    return this.http.get<Terrain[]>(this.apiUrl);
  }
  deleteTerrain(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }
  getTerrainById(id: number): Observable<Terrain> {
    return this.http.get<Terrain>(`${this.apiUrl}/${id}`);
  }
  updateTerrain(id: number, terrain: Terrain): Observable<Terrain> {
    return this.http.put<Terrain>(`${this.apiUrl}/${id}`, terrain);
  }

  // Récupérer les données du capteur pour un terrain
  getSensorData(id: number): Observable<string> {
    const url = `${this.apiUrl}/getSensorData/${id}`;
    return this.http.get<string>(url);
  }

}
