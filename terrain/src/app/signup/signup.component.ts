import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true, // Indique que c'est un Standalone Component
  imports: [CommonModule, FormsModule, RouterModule], // Import des modules nécessaires
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  // Objet de demande d'inscription qui stocke les valeurs des champs du formulaire
  signupRequest = {
    email: '',
    password: '',
    nom: '',
    prenom: '',
    role: '' // Role sélectionné (ADMIN/USER)
  };

  constructor(private http: HttpClient, private router: Router) {}

  // Fonction appelée lors de la soumission du formulaire
  onSubmit() {
    // Appel HTTP pour soumettre les données à l'API de création de compte
    this.http.post<{ role: string }>('http://localhost:8080/api/signup', this.signupRequest)
      .subscribe(
        (response) => {
          console.log('User created:', response);

          // Redirection basée sur le rôle
          if (response.role === 'ADMIN') {
            this.router.navigate(['/admin-dashboard']); // Rediriger vers le tableau de bord Admin
          } else if (response.role === 'USER') {
            this.router.navigate(['/resevation']); // Rediriger vers la page de réservation
          } else {
            alert('Unknown role: ' + response.role); // Rôle non reconnu
          }
        },
        (error) => {
          console.error('Signup error:', error);
          alert(error.error || 'An error occurred'); // Gestion des erreurs
        }
      );
  }
}
