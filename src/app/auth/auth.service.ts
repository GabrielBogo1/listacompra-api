import { Injectable, inject } from '@angular/core';
import { Router } from 'express';
import { User } from '../models/user/user';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../enviroments/enviroment.dev';
import { jwtDecode, JwtPayload } from "jwt-decode";
import { Login } from './login';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  http = inject(HttpClient);
  API = environment.SERVIDOR + "/auth";

  constructor() { }

  login(user: User): Observable<User | null> {
    let foundUser = this.http.post<User | null>(`${this.API}/login`, user, {
      headers: { 'Content-Type': 'application/json' }
    });
    
    return foundUser
  }

  logar(login: User): Observable<string> {
    return this.http.post<string>(`${this.API}/login`, login, {
      headers: { 'Content-Type': 'application/json' },
      responseType: 'text' as 'json'
    });
  }

  register(user: User): Observable<User> {
    console.log(user);
    return this.http.post<User>(`${this.API}/register`, user, {
      headers: { 'Content-Type': 'application/json' },
      responseType: 'text' as 'json'
    });
  }

  addToken(token: string) {
    try {
      const parsed = JSON.parse(token);
      localStorage.setItem('token', parsed.token || token);
    } catch (e) {
      localStorage.setItem('token', token);
    }
  }

  removerToken() {
    localStorage.removeItem('token');
  }

  getToken() {
    const token = localStorage.getItem('token');
    try {
      const parsedToken = JSON.parse(token!);
      return parsedToken.token || token;
    } catch (error) {
      return token;
    }
  }

  jwtDecode() {
    let token = this.getToken();
    if (token) {
      return jwtDecode<JwtPayload>(token);
    }
    return "";
  }

  hasPermission(role: string) {
    
    let user = this.jwtDecode() as any;
    console.log(user.roles[0]);
    if (user.roles[0] == role)
      return true;
    else
      return false;
  }
}
