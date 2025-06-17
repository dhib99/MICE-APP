import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Salle {
  id?: number;
  capacite: number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class SalleService {
  private baseUrl = 'http://localhost:8080/api/salles';

  constructor(private http: HttpClient) {}

  createSalle(salle: Salle): Observable<Salle> {
    return this.http.post<Salle>(this.baseUrl, salle);
  }

  getSalles(): Observable<Salle[]> {
    return this.http.get<Salle[]>(this.baseUrl);
  }

  updateSalle(id: number, salle: Salle): Observable<Salle> {
    return this.http.put<Salle>(`${this.baseUrl}/${id}`, salle);
  }

  deleteSalle(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
