import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
export interface Evenement {
  id: number;
  nom: string;
  dateDebut: string;
  dateFin: string;
  salle?: {
    id: number;
    nom: string;
    capacite: number;
    disponible: boolean;
    equipements?: any[];
  };
  exposition?: {
    exposant: string;
    stands: { nom: string }[];
  };
}


@Injectable({
  providedIn: 'root'
})
export class EvenementService {
  private baseUrl = 'http://localhost:8080/api/evenements';
  // URL de l'API backend

  constructor(private http: HttpClient) {}

  // Créer un nouvel événement (POST)

  createEvenement(payload: any): Observable<any> {
    // Récupérer le token JWT (ou autre) si tu utilises l'authentification
    const token = localStorage.getItem('token');

    // let headers = new HttpHeaders({
    //   'Content-Type': 'application/json'
    // });

    // if (token) {
    //   headers = headers.set('Authorization', `Bearer ${token}`);
    // }

    return this.http.post(this.baseUrl+"/add", payload);
  }

  

  // Récupérer tous les événements (GET)
  getEvenements(): Observable<Evenement[]> {
    return this.http.get<Evenement[]>(this.baseUrl);
  }

  // Récupérer un événement par ID (GET)
  getEvenementById(id: number): Observable<Evenement> {
    return this.http.get<Evenement>(`${this.baseUrl}/${id}`);
  }

  // Mettre à jour un événement (PUT)
  updateEvenement(id: number, evenement: Evenement): Observable<Evenement> {
    return this.http.put<Evenement>(`${this.baseUrl}/${id}`, evenement);
  }

  // Supprimer un événement (DELETE)
  deleteEvenement(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
