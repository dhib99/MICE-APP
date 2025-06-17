import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Stand {
  nom: string;
  x: number;
  y: number;
  width: number;
  height: number;
  disponibilite: boolean;
  expositionId: number;
}

@Injectable({
  providedIn: 'root'
})
export class StandService {

  private apiUrl = 'http://localhost:8080/stands'; // URL de ton API backend

  constructor(private http: HttpClient) {}

  createStand(stand: Stand): Observable<Stand> {
    return this.http.post<Stand>(this.apiUrl, stand);
  }
}
