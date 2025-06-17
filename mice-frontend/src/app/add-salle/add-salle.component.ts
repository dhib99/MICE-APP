import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SalleService } from '../services/salle.service';

export interface Salle {
  id?: number;
  capacite: number;
  name: string;
}

@Component({
  selector: 'app-add-salle',
  standalone: true,
  templateUrl: './add-salle.component.html',
  styleUrls: ['./add-salle.component.css'],
  imports: [
    CommonModule,
    ReactiveFormsModule,
  ]
})
export class AddSalleComponent implements OnInit {
  salleForm!: FormGroup;

  constructor(private fb: FormBuilder, private salleService: SalleService) {}
  ngOnInit(): void {
    this.salleForm = this.fb.group({
      nom: ['', Validators.required],
      capacite: [null, [Validators.required, Validators.min(1)]],
    });
  }
  onSubmit(): void {
    if (this.salleForm.invalid) {
      // Marquer tous les champs comme touchés pour afficher les erreurs
      this.salleForm.markAllAsTouched();
      return;
    }
  
    const nouvelleSalle: Salle = this.salleForm.value;
  
    this.salleService.createSalle(nouvelleSalle).subscribe({
      next: (salle) => {
        console.log('Salle ajoutée:', salle);
        alert('Salle ajoutée avec succès !');  // message à l'utilisateur
        this.salleForm.reset();
      },
      error: (err) => {
        console.error('Erreur ajout salle:', err);
        alert('Erreur lors de l\'ajout de la salle. Veuillez réessayer.');
      }
    });
  }
  
    }
  