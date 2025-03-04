import { Component, inject } from '@angular/core';
import { AuthService } from '../../auth/auth.service';
import { Router } from '@angular/router'; 
import { FormsModule } from '@angular/forms';
import { User } from '../../models/user/user';
import Swal from 'sweetalert2';
import { environment } from '../../enviroments/enviroment.dev';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent {
  login = new User(0, "", "", null, null);
  router = inject(Router);
  authService = inject(AuthService);

  constructor(){
  }

  logar() {
    if(this.login.email == environment.DEV_EMAIL && this.login.password == environment.DEV_MASTERKEY) {
      this.router.navigate(['/listagem'], {state: { user: environment.DEV_USER}});
    } else {
      this.authService.login(this.login).subscribe({
        next: foundUser => { 
          if(foundUser) {
            this.router.navigate(['/listagem'], {state: { user: foundUser}});
          } else {
            Swal.fire({
              title: "Credenciais inválidas!",
              icon: 'warning',
              confirmButtonText: 'Ok'
            });
          }
        },
        error: erro => { 
          Swal.fire({
            title: erro.error ? erro.error.toString()  : erro.message.toString(),
            icon: 'error',
            confirmButtonText: 'Ok'
          });
          console.error(erro);
        }
      });
    }
  }
}
