import { inject } from '@angular/core';
import { Router, CanActivateFn } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  const isAuthenticated = localStorage.getItem('isLoggedIn') === 'true';

  if (isAuthenticated) {
    return true;
  } else {
    return router.createUrlTree(['/login']);
  }
};
