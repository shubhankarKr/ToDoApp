import { CanActivateFn } from '@angular/router';
import { User } from '../model/User';
import { Router } from '@angular/router';
import { inject } from '@angular/core';
import { UserService } from '../service/user.service';

export const authGuard: CanActivateFn = (route, state) => {
  console.log(' route guard');

  const router = inject(Router);
  const userService = inject(UserService)
  let output = false;
  userService.getloggedInUser().subscribe({
    next: res => {
      if (res == true) {
        output = true;
      } else {
        router.navigate(['/login']);
        output = false;
      }
    },
    error: err => console.log(' error in authGuard ' + JSON.stringify(err))

  })
  return output;
};
