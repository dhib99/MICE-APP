import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user.service';
import { User } from '../models/user.model';

@Component({
  selector: 'app-edit-user',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  editUserForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private userService: UserService
  ) {
    this.editUserForm = this.fb.group({
      id: [0],              // Id pour mettre à jour l'utilisateur
      cin: [''],
      userName: [''],
      firstName: [''],
      lastName: [''],
      email: [''],
      password: [''],
 
      creationDate: [''],
      modificationDate: [''],
      status: [true],
      role: ['COMMERCIAL']
    });
  }

  ngOnInit(): void {
    const userId = this.route.snapshot.paramMap.get('id');
    if (userId) {
      this.userService.getUserById(+userId).subscribe(
        (user: User) => {
          this.editUserForm.patchValue(user);
        },
        err => {
          console.error('Erreur lors de la récupération de l’utilisateur', err);
        }
      );
    }
  }

  onSubmit(): void {
    if (this.editUserForm.valid) {
      const updatedUser = this.editUserForm.value;
      this.userService.updateUser(updatedUser).subscribe(
        res => {
          console.log('Utilisateur mis à jour avec succès', res);
          // Par exemple rediriger vers la liste des utilisateurs après update
        },
        err => {
          console.error('Erreur lors de la mise à jour', err);
        }
      );
    }
  }
}
