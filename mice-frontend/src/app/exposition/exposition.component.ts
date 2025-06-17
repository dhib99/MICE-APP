import { Component } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule, FormGroup } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ExpositionService } from '../services/exposition.service';

@Component({
  selector: 'app-exposition',
  templateUrl: './exposition.component.html',
  styleUrls: ['./exposition.component.css'],
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule]
})
export class ExpositionComponent {
  expositionForm: FormGroup;
  expositionTypes = ['TYPE1', 'TYPE2', 'TYPE3'];

  // Injecter ExpositionService 
  constructor(private fb: FormBuilder, private expositionService: ExpositionService) {
    this.expositionForm = this.fb.group({
      nom: ['', Validators.required],
      type: ['', Validators.required],
      maxStands: [null],
    });
  }

  submit() {  
    if (this.expositionForm.valid) {
      const newExpo = this.expositionForm.value;
      console.log('Exposition créée:', newExpo);
      this.expositionService.createExposition(newExpo).subscribe({
        next: data => console.log('Réponse backend:', data),
        error: err => console.error('Erreur backend:', err)
      });
    }
  }
}
