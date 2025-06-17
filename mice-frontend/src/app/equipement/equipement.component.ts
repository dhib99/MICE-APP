import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { EquipementService, Equipement } from '../services/equipement.service';






@Component({
  selector: 'app-equipement',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './equipement.component.html',
  styleUrls: ['./equipement.component.css']

})



export class EquipementComponent {
  nouvelEquipement: Equipement = {
    nom: '',
    description: '' 
  };

  equipements: Equipement[] = [];
  errorMsg = '';
  loading = false;

  constructor(private equipementService: EquipementService) {
    this.chargerEquipements();
  }

  chargerEquipements() {
    this.loading = true;
    this.equipementService.getEquipements().subscribe({
      next: (data) => {
        this.equipements = data;
        this.loading = false;
      },
      error: () => {
        this.errorMsg = 'Erreur lors du chargement des équipements.';
        this.loading = false;
      }
    });
  }

  onSubmit(form: NgForm): void {
    if (form.invalid) {
      form.control.markAllAsTouched();
      return;
    }

    this.equipementService.ajouterEquipement(this.nouvelEquipement).subscribe({
      next: (equipementAjoute) => {
        this.equipements.push(equipementAjoute);
        alert('Équipement ajouté avec succès !');
        form.resetForm();
        this.nouvelEquipement = { nom: '', description: '' };
      },
      error: () => {
        alert('Erreur lors de l\'ajout de l\'équipement. Veuillez réessayer.');
      }
    });
  }

  supprimerEquipement(id: number, index: number) {
    this.equipementService.supprimerEquipement(id).subscribe({
      next: () => {
        this.equipements.splice(index, 1);
      },
      error: () => {
        alert('Erreur lors de la suppression de l\'équipement.');
      }
    });
  }
}
