import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { UserService } from '../services/user.service'; // Assure-toi du bon chemin
import { Validators } from '@angular/forms';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    const { username, password } = this.loginForm.value;
  
    this.userService.login(username, password).subscribe({
      next: (response: any) => {
        const token = response.accessToken;
    
        // 1. Sauvegarder le token
        localStorage.setItem('access_token', token);
    
        // 2. Décoder le token pour extraire les infos utilisateur
        const decoded = this.userService.decodeJwt(token);
        if (decoded) {
          const user = {
            username: decoded.sub,
            role: Array.isArray(decoded.roles) ? decoded.roles[0] : decoded.role || 'ROLE_USER',
            userId:decoded.userId?? null
          };
    
          localStorage.setItem('currentUser', JSON.stringify(user));
    
          console.log('Utilisateur connecté :', user.username);
          console.log('Rôle :', user.role);
        }
    
        // 3. Redirection
        this.router.navigate(['/user']);
      },
      error: (err) => {
        console.error('Erreur de connexion :', err);
        alert('Email ou mot de passe incorrect');
      }
    });
  }    
  
}  