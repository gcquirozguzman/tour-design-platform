import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CardComponent } from 'src/app/theme/shared/components/card/card.component';
import { ClientService } from 'src/app/services/client.service';
import { RecommendationHistoryService } from 'src/app/services/recommendation-history.service';
import { ClientModel } from 'src/app/models/client.model';
import { RecommendationHistoryModel } from 'src/app/models/recommendation-history.model';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-history-experiences-page',
  standalone: true,
  imports: [CommonModule, FormsModule, CardComponent, NgFor, NgIf],
  templateUrl: './history-experiences-page.component.html',
  styleUrls: ['./history-experiences-page.component.scss']
})
export class HistoryExperiencesPageComponent {
  query: string = '';
  clients: ClientModel[] = [];
  selectedClientId: number | null = null;
  loadingClients = false;
  loadingHistory = false;
  history: RecommendationHistoryModel[] = [];
  errorMsg = '';

  constructor(
    private clientService: ClientService,
    private historyService: RecommendationHistoryService
  ) {}

  searchClients() {
    if (!this.query.trim()) return;
    this.loadingClients = true;
    this.clients = [];
    this.history = [];
    this.selectedClientId = null;

    this.clientService.searchByQuery(this.query).subscribe({
      next: (data) => {
        this.clients = data;
        this.loadingClients = false;
      },
      error: (err) => {
        console.error(err);
        this.loadingClients = false;
      }
    });
  }

  onClientSelect(event: Event) {
    const selectEl = event.target as HTMLSelectElement;
    const clientId = Number(selectEl.value);
    if (!clientId) return;

    this.selectedClientId = clientId;
    this.loadHistory(clientId);
  }

  loadHistory(clientId: number) {
    this.loadingHistory = true;
    this.errorMsg = '';
    this.history = [];

    this.historyService.searchByClient(clientId).subscribe({
      next: (data) => {
        this.history = data;
        this.loadingHistory = false;
      },
      error: (err) => {
        console.error(err);
        this.errorMsg = 'No se pudo cargar el historial del cliente.';
        this.loadingHistory = false;
      }
    });
  }
}