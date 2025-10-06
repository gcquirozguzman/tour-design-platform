import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CardComponent } from 'src/app/theme/shared/components/card/card.component';
import { ClientService } from 'src/app/services/client.service';
import { ClientModel } from 'src/app/models/client.model';
import { ClientPreferenceModel } from 'src/app/models/client-preference.model';
import { NgFor, NgIf } from '@angular/common';
import { ClientPreferenceService } from 'src/app/services/client-preference.service';
import { PackageRecommendationService } from 'src/app/services/package-recommendation.service';
import { RecommendationRequest } from 'src/app/models/recommendation-request.model';
import { RecommendationResponse } from 'src/app/models/recommendation-response.model';

@Component({
  selector: 'app-experience-design-page',
  standalone: true,
  imports: [CommonModule, FormsModule, CardComponent, NgFor, NgIf],
  templateUrl: './experience-design-page.component.html',
  styleUrls: ['./experience-design-page.component.scss']
})
export class ExperienceDesignPageComponent {
  query: string = '';
  clients: ClientModel[] = [];
  preferences: ClientPreferenceModel[] = [];
  selectedClientId: number | null = null;
  recommendationResponse?: RecommendationResponse;
  loadingClients: boolean = false;
  loadingPreferences: boolean = false;
  errorMsg: string = '';

  // Para manejar los switches seleccionados
  selectedPreferences: Set<number> = new Set<number>();

  constructor(
    private clientService: ClientService,
    private clientPreferenceService: ClientPreferenceService,
    private packageRecommendationService: PackageRecommendationService,
  ) { }

  searchClients() {
    if (!this.query.trim()) return;

    this.loadingClients = true;
    this.clients = [];
    this.preferences = [];
    this.selectedClientId = null;

    this.clientService.searchByQuery(this.query).subscribe({
      next: (data) => {
        this.clients = data;
        this.loadingClients = false;
      },
      error: (error) => {
        console.error(error);
        this.clients = [];
        this.loadingClients = false;
      }
    });
  }

  onClientSelect(event: Event) {
    const selectEl = event.target as HTMLSelectElement;
    const clientId = Number(selectEl.value);
    if (!clientId) return;

    this.selectedClientId = clientId;
    this.loadPreferences(clientId);
  }

  loadPreferences(clientId: number) {
    this.loadingPreferences = true;
    this.preferences = [];
    this.selectedPreferences.clear();
    this.errorMsg = '';

    this.clientPreferenceService.searchByClient(clientId).subscribe({
      next: (data) => {
        this.preferences = data;
        this.loadingPreferences = false;
      },
      error: (error) => {
        console.error(error);
        this.errorMsg = 'Ocurrió un error al cargar las experiencias.';
        this.loadingPreferences = false;
      }
    });
  }

  togglePreference(prefId: number, checked: boolean) {
    if (checked) {
      this.selectedPreferences.add(prefId);
    } else {
      this.selectedPreferences.delete(prefId);
    }
  }

  generarPaquete() {
    console.log('Cliente ID:', this.selectedClientId);
    console.log('Preferencias seleccionadas:', Array.from(this.selectedPreferences));

    const req: RecommendationRequest = {
      clientId: this.selectedClientId,
      preferenceIds: Array.from(this.selectedPreferences)
    };

    this.packageRecommendationService.recommend(req).subscribe({
      next: (response) => {
        console.log('🎁 Respuesta del backend:', response);
        this.recommendationResponse = response;
      },
      error: (err) => console.error('Error generando paquete:', err)
    });


  }

  onCheckboxChange(prefId: number, event: Event): void {
    const checked = (event.target as HTMLInputElement).checked;
    this.togglePreference(prefId, checked);
  }

}
