import { CanActivateFn } from '@angular/router';
import { User } from '../model/User';
import { Router} from '@angular/router';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
      if(sessionStorage.getItem('userdetails')){
        return true;
      }
      else{
          router.navigate(['/login']);
      }
    return false;
};
