import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { LoginResponse } from '../models/login-response.model';
import { LoginRequest } from '../models/login-request.model';
import { environment } from '../../environments/environment';






import { BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';

import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  testVar = "testing";
  user = new BehaviorSubject<User>(new User());
  currentUser = this.user.asObservable();
  userValue: User = new User();

  constructor(
    private router: Router,
    private http: HttpClient
  ) {
    // Lors de l'initialisation, on observe les changements de l'utilisateur
    this.currentUser.subscribe(
      (currentUser) => {
        this.userValue = currentUser;
      }
    );
  }

  refreshToken() {
    const refreshToken = localStorage.getItem('refreshToken');
    if (refreshToken) {
      return this.http.post(`${environment.baseURL}auth/refresh`, { refreshToken })
        .pipe(
          tap((response: any) => {
            if (response && response.token) {
              localStorage.setItem('jwt', response.token);  // Save the new token
              console.log("Token refreshed successfully");
            }
          })
        );
    }
    return ('No refresh token available');
  }
  
  // Méthode pour obtenir les en-têtes avec le token JWT
  getHeaders(): HttpHeaders {
    const token = this.getJwtFromLocalStorage();
    let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`);
    }
    
    return headers;
  }
  

  // Créer un nouvel utilisateur
  createUser(user: User): Observable<User> {
    const headers = this.getHeaders(); // Assurez-vous que les en-têtes sont bien récupérés
    return this.http.post<User>(`${environment.baseURL}user/create`, user, { headers });
  }
  
  // Se connecter
  login(username: string, password: string): Observable<LoginResponse> {
    const loginRequest = new LoginRequest();
    loginRequest.username = username;
    loginRequest.password = password;
  
    return this.http.post<LoginResponse>(`${environment.baseURL}api/auth/login`, loginRequest)
      .pipe(
        tap((response) => {
          console.log("Connexion réussie :", response);  // Confirme que tu vois bien accessToken
  
          if (response && response.accessToken) {
            const user: User = {
              id: Number(response.userId),
              username: response.username,
              email: response.email,
              role: response.role as 'ADMIN' | 'COMMERCIAL',
              cin: Number(response.cin),
              firstName: response.firstName,
              lastName: response.lastName,
              adresse: response.adresse,
            
              password: '',
              creationDate: new Date(),
              modificationDate: new Date(),
              status: true
            };
  
            this.saveUser(user, response.accessToken); // <-- ICI : utiliser accessToken
          }
        })
      );
  }
  

  // Obtenir tous les utilisateurs
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${environment.baseURL}user/list`, { headers: this.getHeaders() });
  }

  // Obtenir un utilisateur par son ID
  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${environment.baseURL}user/list/${userId}`, { headers: this.getHeaders() });
  }

  // Supprimer un utilisateur
  deleteUser(userId: number): Observable<any> {
    const headers = this.getHeaders();
    console.log('Headers utilisés pour la suppression:', headers.get('Authorization'));
    return this.http.delete<any>(`${environment.baseURL}user/delete/${userId}`, { headers });
  }
  

  // Mettre à jour un utilisateur
  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${environment.baseURL}user/update`, user, { headers: this.getHeaders() });
  }

  // Sauvegarder un utilisateur dans le localStorage
  saveUser(user: User, jwt: string) {
    console.log("Sauvegarde dans localStorage...");
    localStorage.setItem('currentUser', JSON.stringify(user));
    localStorage.setItem('jwt', jwt);
    this.user.next(user);
    console.log("Utilisateur et token sauvegardés dans localStorage");
  }
  
  // Récupérer un utilisateur depuis le localStorage

  // Récupérer le JWT depuis le localStorage
  getJwtFromLocalStorage(): string | null {
    return localStorage.getItem('jwt');
  }

  // Vérifier si l'utilisateur est authentifié
  isAuthenticated(): boolean {
    return !!localStorage.getItem('jwt');
  }

  // Vérifier si l'utilisateur a le rôle Admin
  isAdmin(): boolean {
    const user = this.getUserFromLocalStorage();
    return user ? user.role === 'ADMIN' : false;
  }

  // Se déconnecter
  logout() {
    localStorage.clear(); // Supprimer toutes les données de l'utilisateur du localStorage
    this.router.navigate(['']); // Rediriger vers la page d'accueil
  }

  // Vérifier si le token est valide
  isTokenValid(): boolean {
    const token = this.getJwtFromLocalStorage();
    if (!token) return false;

    const decodedToken = this.decodeJwt(token);
    const expiryDate = decodedToken.exp * 1000; // Convertir l'expiration en millisecondes
    return new Date().getTime() < expiryDate;
  }

  // Décoder un token JWT
   decodeJwt(token: string): any {
    const parts = token.split('.');
    if (parts.length !== 3) {
      throw new Error('Token JWT mal formé');
    }
    const payload = atob(parts[1]);
    return JSON.parse(payload);
  }

  // Exemple de méthode dans UserService
getUserFromLocalStorage(): User | null {
  const userJson = localStorage.getItem('currentUser');
  if (!userJson) return null;
  const user = JSON.parse(userJson);
  return new User(user);  // Ou juste retourner user si tu n'utilises pas la classe
}

  }