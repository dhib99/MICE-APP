import { Component } from '@angular/core';
import { Stand, StandService } from '../services/stand.service';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-stand',
  standalone: true,
  templateUrl: './stand.component.html',
  styleUrl: './stand.component.css',
  imports: [ReactiveFormsModule]
})
export class StandComponent {
  standForm: FormGroup;
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private standService: StandService) {
    this.standForm = this.fb.group({
      nom: ['', Validators.required],
      x: [0, Validators.required],
      y: [0, Validators.required],
      width: [0, Validators.required],
      height: [0, Validators.required],
      disponibilite: [true],
      expositionId: [null, Validators.required]
    });
  }

  onSubmit() {
    if (this.standForm.valid) {
      const stand: Stand = this.standForm.value;
      this.standService.createStand(stand).subscribe({
        next: (res) => {
          this.successMessage = 'Stand créé avec succès !';
          this.errorMessage = '';
          this.standForm.reset();
        },
        error: (err) => {
          this.errorMessage = 'Erreur lors de la création du stand.';
          this.successMessage = '';
          console.error(err);
        }
      });
    } else {
      this.errorMessage = 'Veuillez remplir correctement tous les champs.';
    }
  }
}


