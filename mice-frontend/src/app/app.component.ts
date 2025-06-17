import { Component } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'mice-frontend';
  constructor(public router: Router) {}

  isLoginRoute(): boolean {
    return this.router.url === '/login';
  }
}
