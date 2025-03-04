import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/auth.service';
import Swal from 'sweetalert2';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  imports: [CommonModule, ReactiveFormsModule],
  styleUrl: './cadastro.component.css',
})
export class CadastroComponent {
  cadastroForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService
  ) {
    this.cadastroForm = this.fb.group(
      {
        username: ['', [Validators.required, Validators.minLength(3)]],
        email: ['', [Validators.required, Validators.email]],
        confirmEmail: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(6)]],
        confirmPassword: ['', [Validators.required]],
      },
      { validators: [this.matchFields('email', 'confirmEmail'), this.matchFields('password', 'confirmPassword')] }
    );
  }

  // Validador para verificar se dois campos são iguais
  matchFields(field1: string, field2: string) {
    return (group: AbstractControl): ValidationErrors | null => {
      const value1 = group.get(field1)?.value;
      const value2 = group.get(field2)?.value;
      if (value1 !== value2) {
        group.get(field2)?.setErrors({ notMatching: true });
        return { notMatching: true };
      }
      return null;
    };
  }

  register() {
    if (this.cadastroForm.invalid) {
      Swal.fire({
        title: 'Por favor, corrija os erros no formulário!',
        icon: 'warning',
        confirmButtonText: 'Ok',
      });
      return;
    }

    const user = this.cadastroForm.value;
    this.authService.register(user).subscribe({
      next: (msg) => {
        Swal.fire({
          title: "Cadastro efetuado com sucesso!",
          icon: 'success',
          confirmButtonText: 'Ok',
        }).then(() => {
          this.router.navigate(['/listagem'], { state: { user } });
        });
      },
      error: (erro) => {
        Swal.fire({
          title: erro.error ? erro.error.toString() : erro.message.toString(),
          icon: 'error',
          confirmButtonText: 'Ok',
        });
      },
    });
  }
}
