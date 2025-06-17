import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

export interface RegisterData {
  name: string;
  cin: string;
  username: string;
  firstname: string;
  lastName: string;
  email: string;
  password: string;
  adresse: string;
  role: string;
  status: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private baseUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  signup(data: RegisterData): Observable<any> {
    return this.http.post(`${this.baseUrl}/signup`, data);
  }
  
}
