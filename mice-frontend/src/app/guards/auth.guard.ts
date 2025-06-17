/*import { CanActivateFn } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  return true;
};*/
import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { UserService } from '../services/user.service';


export const authGuard: CanActivateFn = (route, state) => {
  const userService = inject(UserService);
  const router = inject(Router);

  const user = userService.getUserFromLocalStorage();

  if (!user || !userService.isTokenValid()) {
    router.navigate(['/login']);
    return false;
  }

  const ADMIN_ROLE = 'ROLE_ADMIN';
  const USER_ROLE = 'ROLE_USER';

  // Si l'utilisateur est ADMIN, accès complet
  if (user.role === ADMIN_ROLE) return true;

  // Règles d'accès pour ROLE_USER (commercial)
  const allowedRoutesForUser = ['/login', '/evenement'];

  if (
    user.role === USER_ROLE &&
    allowedRoutesForUser.some(path => state.url.startsWith(path))
  ) {
    return true;
  }

  // Sinon, rediriger vers page non autorisée
  router.navigate(['/unauthorized']);
  return false;
};
