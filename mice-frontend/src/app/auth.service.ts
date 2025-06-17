// src/app/auth.service.ts
import { Injectable } from '@angular/core';

export interface User {
  username: string;
  role: 'admin' | 'commercial' ; // you can add more roles
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUser: User | null = null;

  constructor() {
    this.loadUserFromStorage();
  }

  // Fake login function that sets the current user and stores it
  login(username: string, role: User['role']): void {
    this.currentUser = { username, role };
    localStorage.setItem('currentUser', JSON.stringify(this.currentUser));
  }

  // Load user from localStorage on app start
  private loadUserFromStorage(): void {
    const userJson = localStorage.getItem('currentUser');
    if (userJson) {
      this.currentUser = JSON.parse(userJson);
    }
  }

  // Check if user is logged in
  isLoggedIn(): boolean {
    return this.currentUser !== null;
  }

  // Get current logged user role or null if no user
  getUserRole(): User['role'] | null {
    return this.currentUser ? this.currentUser.role : null;
  }

  // Logout user and clear stored data
  logout(): void {
    this.currentUser = null;
    localStorage.removeItem('currentUser');
  }
}
