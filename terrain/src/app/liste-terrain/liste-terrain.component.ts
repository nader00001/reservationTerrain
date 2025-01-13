import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Terrain, TerrainService } from '../terrain.service';
import { TerrainModalComponent } from './terrain-modal/terrain-modal.component';

@Component({
  selector: 'app-liste-terrain',
  standalone: true,
  imports: [
    CommonModule,
    HttpClientModule,
    RouterModule,
    TerrainModalComponent,
  ],
  templateUrl: './liste-terrain.component.html',
  styleUrls: ['./liste-terrain.component.css'],
})
export class ListeTerrainsComponent implements OnInit {
  terrains: Terrain[] = [];
  errorMessage: string = '';
  successMessage: string = '';
  @Input() terrain!: Terrain;

  constructor(private terrainService: TerrainService, private router: Router) {}

  ngOnInit(): void {
    this.loadTerrains();
  }

  loadTerrains(): void {
    this.terrainService.getAllTerrains().subscribe(
      (data) => {
        this.terrains = Array.isArray(data) ? data : [];
      },
      (error) => {
        this.errorMessage = 'Erreur lors de la récupération des terrains.';
        setTimeout(() => (this.errorMessage = ''), 3000);
      }
    );
  }

  deleteTerrain(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce terrain ?')) {
      this.terrainService.deleteTerrain(id).subscribe(
        () => {
          this.successMessage = 'Le terrain a été supprimé avec succès.';
          this.loadTerrains();
          setTimeout(() => (this.successMessage = ''), 3000);
        },
        () => {
          this.errorMessage = 'Erreur lors de la suppression du terrain.';
          setTimeout(() => (this.errorMessage = ''), 3000);
        }
      );
    }
  }

  editTerrain(id: number): void {
    this.router.navigate(['/modifier-terrain', id]);
  }

  selectedTerrain: Terrain | null = null;
  showModal: boolean = false;

  viewDetails(terrain: Terrain): void {
    this.selectedTerrain = terrain;
    this.showModal = true;
  }
}
