import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',    // Apunta a tu archivo HTML
  styleUrl: './login.css'        // Apunta a tu archivo CSS
})
export class LoginComponent {
  credenciales = {
    usuario: '',
    password: ''
  };

  error = false;

  constructor(private router: Router) {}

  ingresar() {
    if (this.credenciales.usuario === 'admin' && this.credenciales.password === 'admin1234') {
      this.error = false;
      localStorage.setItem('isLoggedIn', 'true');
      this.router.navigate(['/productos']);
    } else {
      this.error = true;
      this.credenciales.password = '';
    }
  }
}
