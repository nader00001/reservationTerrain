import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Terrain } from '../../terrain.service';
import { Gymnasium } from '../../gymnasium.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-gymnasium-modal',
  imports: [CommonModule],
  templateUrl: './gymnasium-modal.component.html',
  styleUrl: './gymnasium-modal.component.css'
})
export class GymnasiumModalComponent {
  @Input() terrain!: Gymnasium; // Gymnase à afficher
  @Output() closeModal = new EventEmitter<void>(); // Événement pour fermer le modal

  getAccessibilityMessage(): string {
    const capacityThreshold = 20;

    if (
      this.terrain.capacity !== undefined &&
      this.terrain.gym_count !== undefined &&
      this.terrain.gym_count >= this.terrain.capacity
    ) {
      return `Non accessible car le terrain est plein (Capacité: ${this.terrain.capacity}, Occupation actuelle: ${this.terrain.gym_count}).`;
    } else {
      return `Accessible.`;
    }
  }

  onCloseModal(): void {
    this.closeModal.emit(); // Émettre l'événement pour fermer le modal
  }

}
