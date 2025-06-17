import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Exposition {
  id?: number;
  evenementId: number;
  exposant: string;

}

@Injectable({
  providedIn: 'root'
})
export class ExpositionService {
  private baseUrl = 'http://localhost:8080/api/expositions';

  constructor(private http: HttpClient) {}

  createExposition(exposition: Exposition): Observable<Exposition> {
    return this.http.post<Exposition>(this.baseUrl, exposition);
  }

  getExpositions(): Observable<Exposition[]> {
    return this.http.get<Exposition[]>(this.baseUrl);
  }

  getExpositionByEvenementId(evenementId: number): Observable<Exposition[]> {
    return this.http.get<Exposition[]>(`${this.baseUrl}/evenement/${evenementId}`);
  }

  updateExposition(id: number, exposition: Exposition): Observable<Exposition> {
    return this.http.put<Exposition>(`${this.baseUrl}/${id}`, exposition);
  }

  deleteExposition(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
  
  }
  

