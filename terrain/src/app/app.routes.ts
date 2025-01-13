import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ReservationComponent } from './reservation/reservation.component';
import { SignupComponent } from './signup/signup.component';
import { GymnasiumreservationComponent } from './gymnasiumreservation/gymnasiumreservation.component';
import { TerrainCreationComponent } from './terrain-creation/terrain-creation.component';
import { ListeTerrainsComponent } from './liste-terrain/liste-terrain.component';
import { ModifierTerrainComponent } from './modifier-terrain/modifier-terrain.component';
import { ListeReservationgymComponent } from './reservation-gym/reservation-gym.component';
import { ReservationTerrainComponent } from './reservation-terrain/reservation-terrain.component';
import { GymnasiumComponent } from './gymnasium/gymnasium.component';
import { ListeGymnasiumComponent } from './liste-gymnasium/liste-gymnasium.component';
import { GymnasiumUpdateComponent } from './gymnasium-update/gymnasium-update.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AcceuilComponent } from './acceuil/acceuil.component';

export const routes: Routes = [
  { path: 'signup', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'acceuil', component: AcceuilComponent },
  { path: 'resevation', component: ReservationComponent },
  { path: 'Gymnasiumreservation', component: GymnasiumreservationComponent },
  { path: 'TerrainCreation', component: TerrainCreationComponent },
  { path: 'liste-terrain', component: ListeTerrainsComponent },
  { path: 'modifier-terrain/:id', component: ModifierTerrainComponent },
  { path: 'reservation-gym', component: ListeReservationgymComponent },
  { path: 'ReservationTerrain', component: ReservationTerrainComponent },
  { path: 'Gymnasium', component: GymnasiumComponent },
  { path: 'ListeGymnasium', component: ListeGymnasiumComponent },
  { path: 'gymnasiums/:id/update', component: GymnasiumUpdateComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent },

  // Redirection par défaut à l'accueil ou à la page de connexion
  { path: '', redirectTo: '/acceuil', pathMatch: 'full' },
  { path: '**', redirectTo: '/acceuil' },  // Gérer les routes inconnues
];
