import { Component, Input } from '@angular/core';
import { Terrain } from '../../terrain.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-terrain-modal',
  imports: [CommonModule],
  templateUrl: './terrain-modal.component.html',
  styleUrl: './terrain-modal.component.css'
})
export class TerrainModalComponent {

  @Input() terrain!: Terrain; // Terrain à afficher
  @Input() showModal: boolean = false; // Contrôle l'affichage du modal

  closeModal(): void {
    this.showModal = false;
  }

  getAccessibilityMessage(): string {
    const humidityThreshold = 70;
    const capacityThreshold = 10;

    if (this.terrain.soil_moisture !== undefined && this.terrain.soil_moisture > humidityThreshold) {
      return `Non accessible en raison de l'humidité élevée (${this.terrain.soil_moisture}%).`;
    } else if (
      this.terrain.terrain_count !== undefined &&
      this.terrain.capacity !== undefined &&
      this.terrain.terrain_count >= this.terrain.capacity
    ) {
      return `Non accessible car le terrain est plein (Capacité: ${this.terrain.capacity}, Occupation actuelle: ${this.terrain.terrain_count}).`;
    } else {
      return `Accessible.`;
    }
  }

}
