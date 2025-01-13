import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  // Formulaire de connexion
  loginRequest = {
    email: '',
    password: ''
  };

  // Message d'erreur ou succès
  message: string = '';

  constructor(private readonly router: Router, private http: HttpClient) {}

  // Méthode appelée lors de la soumission du formulaire
  onSubmit() {
    console.log('Tentative de connexion avec :', this.loginRequest);

    this.http.post<any>('http://localhost:8080/auth/login', this.loginRequest, {
      headers: { 'Content-Type': 'application/json' }
    })
    .subscribe(
      (response) => {
        console.log(response);
        if (response) {
          const role = response.utilisateur.role; // Récupère le rôle de la réponse

          // Stocke les informations utilisateur dans localStorage
          localStorage.setItem('user', JSON.stringify(response));

          // Redirection en fonction du rôle
          if (role =='ADMIN') {
            this.router.navigate(['/admin-dashboard']); // Redirige vers Admin Dashboard
          } else if (role === 'USER') {
            this.router.navigate(['/resevation']); // Redirige vers la page Réservation
          } else {
            this.message = 'Rôle inconnu pour cet utilisateur.';
          }
        }
      },
      (error) => {
        // Gestion des erreurs
        console.error('Erreur de connexion', error);
        this.message = 'Échec de la connexion. Vérifiez vos informations.';
      }
    );
  }

}
