import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { StepsModule } from 'primeng/steps';
import { EvenementService } from '../services/evenement.service';
import { SalleService } from '../services/salle.service';
import { ExpositionService } from '../services/exposition.service';
import { EquipementService } from '../services/equipement.service';

import { Router } from '@angular/router';





interface Stand {
  id: number;
  nom: string;
  x: number;
  y: number;
  width: number;
  height: number;
  reservedBy?: string;
}

interface StandReservation {
  stand: Stand;
  reservationName: string;
}
export interface Salle {
  id?: number;
  capacite: number;
  name: string;
}

export interface Equipement {
  id?: number;
  nom: string;
  description: string; 
}




@Component({
  selector: 'app-evenement',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule,  StepsModule],
  templateUrl: './evenement.component.html',
  styleUrls: ['./evenement.component.css']
})
export class EvenementComponent {
  selectedExposition: any | null = null;
  equipements: Equipement[] = [];
  selectedEquipementId?: number;
  isSelected: boolean = false;


  activeIndex: 0 | 1 | 2 = 0;





  steps = [
    { label: 'Choix & Infos' },
    { label: 'Formulaire Exposition / Salle' },
    { label: 'Confirmation' }
  ];

  reservationForm: FormGroup;
  expositionForm: FormGroup;
  salleForm: FormGroup;

  eventTypes = [
    { value: 'exposition', label: 'Exposition' },
    { value: 'salle', label: 'Salle' }
  ];

  confirmationData: any;

  // Stands disponibles pour l'exposition

 stands: Stand[] = [
  { id: 1, nom: 'A1', x: 10, y: 10, width: 80, height: 60 },
  { id: 2, nom: 'A2', x: 100, y: 10, width: 80, height: 60 },
  { id: 3, nom: 'A3', x: 190, y: 10, width: 80, height: 60 },
  { id: 4, nom: 'A4', x: 280, y: 10, width: 80, height: 60 },
  { id: 5, nom: 'A5', x: 370, y: 10, width: 80, height: 60 },
  { id: 6, nom: 'A6', x: 460, y: 10, width: 80, height: 60 },
  { id: 7, nom: 'A7', x: 550, y: 10, width: 80, height: 60 },
  { id: 8, nom: 'A8', x: 640, y: 10, width: 80, height: 60 },
  { id: 9, nom: 'B1', x: 10, y: 80, width: 80, height: 60 },
  { id: 10, nom: 'B2', x: 100, y: 80, width: 80, height: 60 },
  { id: 11, nom: 'B3', x: 190, y: 80, width: 80, height: 60 },
  { id: 12, nom: 'B4', x: 280, y: 80, width: 80, height: 60 },
  { id: 13, nom: 'B5', x: 370, y: 80, width: 80, height: 60 },
  { id: 14, nom: 'B6', x: 460, y: 80, width: 80, height: 60 },
  { id: 15, nom: 'B7', x: 550, y: 80, width: 80, height: 60 },
  { id: 16, nom: 'B8', x: 640, y: 80, width: 80, height: 60 },
  { id: 17, nom: 'C1', x: 10, y: 150, width: 80, height: 60 },
  { id: 18, nom: 'C2', x: 100, y: 150, width: 80, height: 60 },
  { id: 19, nom: 'C3', x: 190, y: 150, width: 80, height: 60 },
  { id: 20, nom: 'C4', x: 280, y: 150, width: 80, height: 60 },
  { id: 21, nom: 'C5', x: 370, y: 150, width: 80, height: 60 },
  { id: 22, nom: 'C6', x: 460, y: 150, width: 80, height: 60 },
  { id: 23, nom: 'C7', x: 550, y: 150, width: 80, height: 60 },
  { id: 24, nom: 'C8', x: 640, y: 150, width: 80, height: 60 },
  { id: 25, nom: 'D1', x: 10, y: 220, width: 80, height: 60 },
  { id: 26, nom: 'D2', x: 100, y: 220, width: 80, height: 60 },
  { id: 27, nom: 'D3', x: 190, y: 220, width: 80, height: 60 },
  { id: 28, nom: 'D4', x: 280, y: 220, width: 80, height: 60 },
  { id: 29, nom: 'D5', x: 370, y: 220, width: 80, height: 60 },
  { id: 30, nom: 'D6', x: 460, y: 220, width: 80, height: 60 },
  { id: 31, nom: 'D7', x: 550, y: 220, width: 80, height: 60 },
  { id: 32, nom: 'D8', x: 640, y: 220, width: 80, height: 60 },
];

  
      
  
selectedSalle: Salle | null = null;

selectRoom(salle: Salle) {
  this.selectedSalle =salle;
  /**
   * 
   * if (this.selectedSalle && this.selectedSalle.id === salle.id) {
    this.selectedSalle = null; // désélectionner si on reclique
  } else {
    this.selectedSalle = salle;
   
   
    
  }
   */
  
}

  selectedStands: StandReservation[] = [];
  salles: Salle[] = [];//tab de stock
  errorMessage: string = ''; //déclaration de la propriété pour stocker un message d'err
  constructor(
    private fb: FormBuilder,
    private evenementService: EvenementService,
    private expositionService: ExpositionService,
    private salleService: SalleService,
    private equipementService: EquipementService,
    private router: Router

  ) {
    this.reservationForm = this.fb.group({
      eventType: ['', Validators.required],
      eventName: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
    });

    this.expositionForm = this.fb.group({
      exposant: ['', Validators.required],
     
    });

    this.salleForm = this.fb.group({
    
      equipement: ['', Validators.required]
    });
  }
  ngOnInit(): void {
    this.loadSalles();
    this.loadEquipements();
  }
  loadEquipements() {
    this.equipementService.getEquipements().subscribe({
      next: (data) => {
        this.equipements = data;
      },
      error: (error) => {
        console.error("Erreur lors du chargement des équipements", error);
      }
    });
  }
  onExpositionSelected(event: any) {
    this.selectedExposition = event.value;  // ou event.target.value selon la méthode
  }
  loadSalles() {
    this.salleService.getSalles().subscribe({
      next: (data) => {
        console.log('Salles reçues:', data);
        // Si la propriété est 'nom' au lieu de 'name', on la mappe ici :
        this.salles = data.map((salle: any) => ({
          id: salle.id,
          name: salle.name || salle.nom || 'Sans nom',
          capacite: salle.capacite
        }));
      },
      error: (error) => {
        this.errorMessage = "Erreur lors du chargement des salles : " + error.message;
      }
    });
  }
  


  prevStep() {
    if (this.activeIndex > 0) {
      this.activeIndex = (this.activeIndex - 1) as 0 | 1 | 2; // optionnel, ou faire un cast en toute sécurité
    }
  }
  
  nextStep() {
    if (this.activeIndex < 2) {
      this.activeIndex = (this.activeIndex + 1) as 0 | 1 | 2;
    }
  }
  
  goToStep(index: number) {
    if (index >= 0 && index <= 2) {
      this.activeIndex = index as 0 | 1 | 2;
    }}

  isCurrentStepValid(): boolean {
    if (this.activeIndex === 0) {
      return this.reservationForm.valid;
    }
    if (this.activeIndex === 1) {
      if (this.reservationForm.value.eventType === 'exposition') {
        return this.expositionForm.valid;
      }
      if (this.reservationForm.value.eventType === 'salle') {
        return this.salleForm.valid;
      }
    }
    if (this.activeIndex === 2) {
      // You can add any confirmation validation if needed
      return true;
    }
    return false;
  }
  

  onNextClicked() {
    if (!this.isCurrentStepValid()) {
      alert('Veuillez remplir correctement le formulaire avant de continuer.');
      return;
    }
  
    if (this.activeIndex === 0) {
      this.nextStep();
    } else if (this.activeIndex === 1) {
      this.submit(); // submit and move to confirmation
    } else if (this.activeIndex === 2) {
      // Maybe finalize or close modal here
      alert('Événement confirmé !');
    }
  }
  

  toggleStandSelection(stand: Stand) {
    const index = this.selectedStands.findIndex(s => s.stand.id === stand.id);
    if (index > -1) {
      this.selectedStands.splice(index, 1);
    } else {
      this.selectedStands.push({ stand, reservationName: '' });
    }
    console.log('Stands sélectionnés:', this.selectedStands.map(s => s.stand.nom));
  }
  

  isStandSelected(stand: Stand): boolean {
    return this.selectedStands.some(s => s.stand.id === stand.id);
  }

  submit() {
    if (!this.reservationForm.valid) {
      alert('Formulaire général invalide');
      return;
    }
    const user = JSON.parse(localStorage.getItem('currentUser')?? "");
    const baseData = this.reservationForm.value;
    let payload: any = {
      nom: baseData.eventName,
      dateDebut: baseData.startDate,
      dateFin: baseData.endDate,
      eventType: baseData.eventType,
      userId: user?.userId?? null
    };
  
    if (baseData.eventType === 'exposition') {
      if (!this.expositionForm.valid) {
        alert('Formulaire exposition invalide');
        return;
      }
      payload = {
        ...payload,
        exposant: this.expositionForm.value.exposant,
        stands: this.selectedStands.map(s => ({
          standId: s.stand.id,
          reservationName: s.reservationName
        }))
        
      };
      expositionId: this.selectedExposition?.id 
    } else if (baseData.eventType === 'salle') {
      if (!this.salleForm.valid || !this.selectedSalle) {
        alert('Formulaire salle invalide ou salle non sélectionnée');
        return;
      }
      payload = {
        ...payload,
        salleId: this.selectedSalle.id,
        equipementId: parseInt(this.salleForm.value.equipement) // ou adapter selon le backend
      };
    }
 
    
  
    const jwt = localStorage.getItem('jwt');
    console.log('Token utilisé:', jwt);
    console.log('Payload envoyé:', payload);
  
    this.evenementService.createEvenement(payload).subscribe({
      next: (response) => {
        alert('Événement créé avec succès !');
        // Naviguer vers le dashboard après soumission réussie
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        alert('Erreur lors de la création de l\'événement');
        console.error(error);
      }
    });
  }
  prepareConfirmation() {
    const type = this.reservationForm.value.eventType;
    this.confirmationData = {
      eventType: type,
      eventName: this.reservationForm.value.eventName,
      startDate: this.reservationForm.value.startDate,
      endDate: this.reservationForm.value.endDate,
    };
  
    if (type === 'exposition') {
      this.confirmationData.exposant = this.expositionForm.value.exposant;
      this.confirmationData.stands = [...this.selectedStands];
    } else if (type === 'salle') {
      this.confirmationData.salle = this.selectedSalle;
      this.confirmationData.equipement = this.equipements.find(eq => eq.id === +this.salleForm.value.equipement);
    }
  }
  nextStepconfirm() {
    if (this.activeIndex === 1) {
      const type = this.reservationForm.value.eventType;
  
      // Validation formulaire exposition
      if (type === 'exposition') {
        if (this.expositionForm.invalid || this.selectedStands.length === 0) {
          this.expositionForm.markAllAsTouched();
          if (this.selectedStands.length === 0) alert('Veuillez sélectionner au moins un stand.');
          return;
        }
      }
      // Validation formulaire salle
      else if (type === 'salle') {
        if (this.salleForm.invalid || !this.selectedSalle) {
          this.salleForm.markAllAsTouched();
          if (!this.selectedSalle) alert('Veuillez sélectionner une salle.');
          return;
        }
      }
  
      // Préparer les données de confirmation
      this.prepareConfirmation();
  
      // Passer à l’étape suivante
      this.nextStep();
    }
  }
  
}
