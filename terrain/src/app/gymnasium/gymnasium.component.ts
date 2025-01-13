import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-gymnasium',
  templateUrl: './gymnasium.component.html',
    imports: [FormsModule,CommonModule],

  styleUrls: ['./gymnasium.component.css'],
})
export class GymnasiumComponent {
  // Structure des données pour le gymnase
  gymnasium = {
    name: '',
    location: '',
    capacity: 0,
  };

  message = ''; // Message de succès
  error = '';   // Message d'erreur

  constructor(private http: HttpClient) {}

  // Méthode appelée lors de la soumission du formulaire
  onSubmit(gymnasiumForm: any) {
    const apiUrl = 'http://localhost:8080/api/gymnasiums'; // URL du backend

    this.http.post(apiUrl, this.gymnasium).subscribe(
      (response) => {
        console.log(response);
        this.message = 'Gymnase créé avec succès !';
        this.error = '';

        // Réinitialiser le formulaire
        gymnasiumForm.reset();
      },
      (err) => {
        console.error(err);
        this.message = '';
        this.error = "Erreur lors de la création du gymnase.";
      }
    );
  }
}
