import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CardComponent } from 'src/app/theme/shared/components/card/card.component';
import { ClientService } from 'src/app/services/client.service';
import { ClientModel } from 'src/app/models/client.model';
import { NgFor, NgIf } from '@angular/common';

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
  loading: boolean = false;

  constructor(private clientService: ClientService) {}

  searchClients() {
    if (!this.query.trim()) return;

    this.loading = true;
    this.clientService.searchByQuery(this.query).subscribe({
      next: (data) => {
        
        console.info(data);

        this.clients = data;
        this.loading = false;
      },
      error: (error) => {

        console.info(error);

        this.clients = [];
        this.loading = false;
      }
    });
  }
}