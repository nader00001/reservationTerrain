import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Gymnasium, GymnasiumService } from '../gymnasium.service';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { GymnasiumModalComponent } from './gymnasium-modal/gymnasium-modal.component';

@Component({
  selector: 'app-liste-gymnasium',
  templateUrl: './liste-gymnasium.component.html',
  imports: [CommonModule, HttpClientModule, RouterModule , GymnasiumModalComponent], // Les modules nécessaires
  styleUrls: ['./liste-gymnasium.component.css'],
})
export class ListeGymnasiumComponent implements OnInit {
  errorMessage: string = '';
  successMessage: string = '';
  gymnasiums: any[] = []; // Initialiser comme un tableau vide
  countGymn ? : number ;
  showModal: boolean = false;
  selectedGymnasium: any;
  constructor(
    private readonly gymnasiumService: GymnasiumService,
    private readonly router: Router
  ) {}

  ngOnInit(): void {
    this.loadGymnasiums(); // Charger les gymnases au démarrage
  }

  // Charger tous les gymnases
  loadGymnasiums(): void {
    this.gymnasiumService.getAllGymnasiums().subscribe(
      (data) => {
        console.log('Gymnases récupérés :', data);
        if (Array.isArray(data)) {
          this.gymnasiums = data;
        } else {
          this.gymnasiums = [];
        }
      },
      (error) => {
        console.error('Erreur lors de la récupération des gymnases :', error);
        this.errorMessage =
          'Erreur lors de la récupération des gymnases : ' + error.message;
        setTimeout(() => {
          this.errorMessage = '';
        }, 3000);
      }
    );
  }

  deleteGymnasium(id: number): void {
    console.log('Tentative de suppression du gymnase avec ID :', id);
    this.gymnasiumService.deleteGymnasium(id).subscribe(
      () => {
        console.log('Gymnase supprimé avec succès.');
        this.successMessage = 'Gymnase supprimé avec succès.';
        this.loadGymnasiums(); // Recharge la liste des gymnases après suppression
        setTimeout(() => {
          this.successMessage = ''; // Efface le message après 3 secondes
        }, 3000);
      },
      (error) => {
        console.error('Erreur lors de la suppression :', error);
        this.errorMessage = 'Erreur lors de la suppression : ' + error.message;
        setTimeout(() => {
          this.errorMessage = ''; // Efface le message après 3 secondes
        }, 3000);
      }
    );
  }




  // Modifier un gymnase : Rediriger vers la page de modification
  editGymnasium(id: number): void {
    this.router.navigate([`/gymnasiums/update/${id}`]);  // Redirige vers la page de modification avec l'ID
  }


  selectedTerrain: Gymnasium | null = null;

    viewDetails(gymnasium: Gymnasium): void {
      this.selectedTerrain = gymnasium;
      this.showModal = true;
    }

}
