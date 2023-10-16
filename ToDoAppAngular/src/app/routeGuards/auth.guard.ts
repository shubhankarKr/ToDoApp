import { CanActivateFn } from '@angular/router';
import { User } from '../model/User';
import { Router} from '@angular/router';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  console.log(' authGuard called ');
  const router = inject(Router);

    //   if(sessionStorage.getItem('userdetails')){
    //     user = JSON.parse(sessionStorage.getItem('userdetails')!);
    //   }
    //   if(!user){
    //     let router= new Router();
    //     router.navigate(['/login']);
    //   }
    // return user?true:false;
    let xsrf = sessionStorage.getItem('token');
    
      if(xsrf){
        console.log(xsrf);
        console.log(JSON.parse(sessionStorage.getItem('userDetails')!));
        
        return true;
      }
      else{
          router.navigate(['/login']);
      }
    return false;
};
