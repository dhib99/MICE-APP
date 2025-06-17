import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // <- important pour les pipes comme date
import { Evenement, EvenementService } from '../services/evenement.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,        // ton composant est standalone
  imports: [CommonModule], // <- ici tu dois importer CommonModule pour avoir les pipes comme date
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  evenements: Evenement[] = [];
  loading = false;
  errorMsg = '';

  constructor(private evenementService: EvenementService) {}

  ngOnInit(): void {
    this.loadEvenements();
  }

  loadEvenements(): void {
    this.loading = true;
    this.errorMsg = '';
    this.evenementService.getEvenements().subscribe({
      next: (data) => {
        console.log("✔️ Données reçues :", data); // DEBUG
        this.evenements = data;
        this.loading = false;
      },
      error: (error) => {
        this.errorMsg = 'Erreur lors du chargement des événements.';
        console.error('❌ Erreur API:', error);
        this.loading = false;
      }
    });
  }
  
}
