import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';
import { CardComponent } from 'src/app/theme/shared/components/card/card.component';
import { ClientService } from 'src/app/services/client.service';
import { ClientPreferenceService } from 'src/app/services/client-preference.service';
import { ClientModel } from 'src/app/models/client.model';
import { ClientPreferenceModel } from 'src/app/models/client-preference.model';
import { debounceTime, switchMap, of } from 'rxjs';

@Component({
  selector: 'app-experience-design-page',
  standalone: true,
  imports: [
    CommonModule, 
    CardComponent, 
    FormsModule, 
    NgSelectModule
  ],
  templateUrl: './experience-design-page.component.html',
  styleUrls: ['./experience-design-page.component.scss']
})
export class ExperienceDesignPageComponent {

  form: FormGroup;
  clients: ClientModel[] = [];
  preferences: ClientPreferenceModel[] = [];
  loadingClients = false;
  loadingPreferences = false;

  constructor(
    private fb: FormBuilder,
    private clientService: ClientService,
    private preferenceService: ClientPreferenceService
  ) {
    this.form = this.fb.group({
      client: [null],
      preferences: [{ value: [], disabled: true }]
    });

    // Busca clientes con debounce
    this.form.get('client')?.valueChanges
      .pipe(
        debounceTime(400),
        switchMap(value => {
          if (!value) {
            this.preferences = [];
            this.form.get('preferences')?.disable();
            return of([]);
          }
          this.loadingClients = true;
          return this.clientService.searchByQuery(value);
        })
      )
      .subscribe(clients => {
        this.clients = clients;
        this.loadingClients = false;
      });

    // Cuando se selecciona un cliente, habilita preferencias
    this.form.get('client')?.valueChanges.subscribe(client => {
      if (client && client.id) {
        this.loadingPreferences = true;
        this.preferenceService.searchByClient(client.id).subscribe(prefs => {
          this.preferences = prefs;
          this.form.get('preferences')?.enable();
          this.loadingPreferences = false;
        });
      } else {
        this.preferences = [];
        this.form.get('preferences')?.disable();
      }
    });
  }
}
