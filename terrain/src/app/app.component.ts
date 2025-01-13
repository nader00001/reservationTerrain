import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';  // Import nécessaire pour utiliser <router-outlet>

@Component({
  selector: 'app-root',
  standalone: true,  // Composant autonome
  imports: [RouterModule],  // Importer RouterModule
  template: `
    <router-outlet></router-outlet>  <!-- Le contenu de la route sera rendu ici -->
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent {}
