import { Injectable, inject } from '@angular/core';
import { Router } from 'express';
import { User } from '../models/user/user';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../enviroments/enviroment.dev';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  http = inject(HttpClient);
  APIAuth = environment.SERVIDOR + "/auth";
  APIUser = environment.SERVIDOR + "/users";

  constructor() { }

  login(user: User): Observable<User | null> {
    let foundUser = this.http.post<User | null>(`${this.APIAuth}/login`, user, {
      headers: { 'Content-Type': 'application/json' }
    });
    
    return foundUser
  }

  register(user: User): Observable<User> {
    return this.http.post<User>(`${this.APIUser}/register`, user, {
      headers: { 'Content-Type': 'application/json' }
    });
  }
}
