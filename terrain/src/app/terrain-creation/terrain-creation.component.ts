import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-terrain-creation',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './terrain-creation.component.html',

  styleUrls: ['./terrain-creation.component.css'],
})
export class TerrainCreationComponent {
  // Structure des données
  terrain = {
    name: '',
    location: '',
    capacity: 0,
    type: '',
  };

  message = ''; // Message de succès
  error = '';   // Message d'erreur
  successMessage: string | undefined;

  constructor(private http: HttpClient) {}

  // Méthode appelée lors de la soumission du formulaire
  onSubmit(terrainForm: any) {
    const apiUrl = 'http://localhost:8080/api/terrains';

    this.http.post(apiUrl, this.terrain).subscribe(
      (response) => {
        console.log(response);
        this.message = 'Terrain créé avec succès !';
        this.error = '';
        this.successMessage = 'Le terrain a été ajouté avec succès.';

        // Réinitialiser le formulaire, y compris la validation
        terrainForm.reset();
      },
      (err) => {
        console.error(err);
        this.message = '';
        this.error = "Erreur lors de la création du terrain.";
        this.successMessage = '';
      }
    );
  }



}
