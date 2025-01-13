import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { TerrainService, Terrain } from '../terrain.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-modifier-terrain',
  templateUrl: './modifier-terrain.component.html',
    imports: [CommonModule, FormsModule, RouterModule],  // Import des modules nécessaires
  
  styleUrls: ['./modifier-terrain.component.css']
})
export class ModifierTerrainComponent implements OnInit {
  terrain: Terrain = { id: 0, name: '', location: '', capacity: 0, type: '' };
  successMessage: string = '';
  errorMessage: string = '';





  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private terrainService: TerrainService
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.terrainService.getTerrainById(id).subscribe(
      (data) => (this.terrain = data),
      (error) => {
        console.error('Erreur lors du chargement du terrain', error);
        this.errorMessage = 'Impossible de charger les informations du terrain.';
      }
    );
  }

  updateTerrain(): void {
    this.terrainService.updateTerrain(this.terrain.id, this.terrain).subscribe(
      () => {
        this.successMessage = 'Le terrain a été modifié avec succès.';
        setTimeout(() => {
          this.router.navigate(['/']);
        }, 2000);
      },
      (error) => {
        console.error('Erreur lors de la mise à jour du terrain', error);
        this.errorMessage = 'Erreur lors de la mise à jour du terrain.';
      }
    );
  }
}
