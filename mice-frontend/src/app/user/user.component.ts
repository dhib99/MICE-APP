import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../models/user.model';
import { Router } from '@angular/router'; 
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
  imports: [CommonModule]
})
export class UserComponent implements OnInit {
  users: User[] = [];
  errorMsg: string = '';

  constructor(private userService: UserService,private router: Router) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getUsers().subscribe({
      next: (data) => {
        this.users = data;
      },
      error: (err) => {
        console.error('Erreur chargement utilisateurs', err);
        this.errorMsg = 'Impossible de charger les utilisateurs';
      }
    });
  }
  editUser(user: User) {
    console.log('Editing user with id:', user.id);
    this.router.navigate(['/edit-user', user.id]);
  }
  
  
  
  deleteUser(userId: number): void {
    this.userService.deleteUser(userId).subscribe({
      next: () => {
        // rafraîchir la liste après suppression
        this.loadUsers();
      },
      error: (err) => {
        console.error('Erreur suppression utilisateur', err);
        this.errorMsg = 'Erreur lors de la suppression';
      }
    });
  }


}
