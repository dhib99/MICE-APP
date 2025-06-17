import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { RegisterService, RegisterData } from '../services/register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  imports: [ReactiveFormsModule]
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  submitted: boolean = false;
  successMsg: string = '';
  errorMsg: string = '';

  constructor(private fb: FormBuilder, private registerService: RegisterService, private router: Router) {
    this.registerForm = this.fb.group({
      cin: ['', Validators.required],
      username: ['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      adresse: ['', Validators.required],
      role: ['', Validators.required],
      status: [false]
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    this.submitted = true;
  
    if (this.registerForm.invalid) {
      return;
    }
  
    const formData = this.registerForm.value;
  
    this.registerService.signup(formData).subscribe({
      next: (response) => {
        console.log('Inscription réussie', response);
        this.successMsg = "Inscription réussie !";
        this.errorMsg = '';
  
        // Assuming response contains the created user with an 'id' field
        const newUserId = response.id;
  
        // Navigate to UserComponent 
     // Correct
this.router.navigate(['/user']);

  
        this.registerForm.reset();
        this.submitted = false;
      },
      error: (error) => {
        console.error('Erreur inscription', error);
        this.errorMsg = "Erreur lors de l'inscription.";
        this.successMsg = '';
      }
    });
  }
}
