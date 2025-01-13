import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

interface LoginRequest {
  email: string;
  password: string;
}

interface LoginResponse {
  token: string;
  utilisateur: any;  // L'utilisateur peut être un objet avec des propriétés comme id, nom, etc.
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  getUser() {
    throw new Error('Method not implemented.');
  }
  // Définir l'URL de base pour l'API, et s'assurer qu'elle est bien formatée
  private apiUrl = 'http://localhost:8080/auth/login';  // Correctement définie sans `${}` autour de l'URL

  constructor(private http: HttpClient) {}

  // Fonction de connexion
  login(request: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.apiUrl, request);
  }

  // Optionnel : Méthode pour vérifier si l'utilisateur est connecté en vérifiant le token JWT
  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');  // Vérifie la présence du token dans le localStorage
  }

  // Sauvegarde du token JWT dans le localStorage (ou sessionStorage)
  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  // Récupère le token JWT depuis le localStorage
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  // Déconnexion
  logout(): void {
    localStorage.removeItem('token');
  }

  // Optionnel : Méthode pour ajouter le token JWT aux entêtes des requêtes
  private getAuthHeaders(): HttpHeaders {
    const token = this.getToken();
    let headers = new HttpHeaders();
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`);
    }
    return headers;
  }


}
