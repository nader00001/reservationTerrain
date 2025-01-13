// gymnasium-update.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GymnasiumService } from '../gymnasium.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-gymnasium-update',
  standalone: true,  // Déclaration du composant comme autonome
    imports: [CommonModule, FormsModule, RouterModule],  // Import des modules nécessaires
  templateUrl: './gymnasium-update.component.html',
  styleUrls: ['./gymnasium-update.component.css']
})
export class GymnasiumUpdateComponent implements OnInit {
  gymnasium: any = {};  // Utilisation d'un objet générique (any) pour les données du gymnase
  id: number | undefined;
  successMessage: string | undefined;
  errorMessage: string | undefined;

  constructor(
    private gymnasiumService: GymnasiumService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Récupérer l'ID du gymnase à partir de l'URL
    this.id = +this.route.snapshot.paramMap.get('id')!;
    this.getGymnasiumDetails(this.id);
  }

  getGymnasiumDetails(id: number): void {
    this.gymnasiumService.getGymnasiumById(id).subscribe(
      (data: any) => {
        console.log('Détails du gymnase récupérés :', data);
        this.gymnasium = data;  // Affecter la réponse à l'objet gymnasium
      },
      (error: any) => {
        console.error('Erreur lors de la récupération du gymnase', error);
        this.errorMessage = 'Erreur lors de la récupération des données.';
      }
    );
  }


  updateGymnasium(): void {
    if (this.id !== undefined) {
      this.gymnasiumService.updateGymnasium(this.id, this.gymnasium).subscribe(
        (response) => {
          console.log('Gymnase mis à jour avec succès', response);
          this.successMessage = 'Gymnase mis à jour avec succès';
          this.router.navigate(['/gymnasiums']);  // Rediriger après la mise à jour
        },
        (error) => {
          console.error('Erreur lors de la mise à jour', error);
          this.errorMessage = 'Erreur lors de la mise à jour du gymnase';
        }
      );
    }
  }
}
