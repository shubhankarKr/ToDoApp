import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor() { }

  loggedInUser: Observable<boolean> = new BehaviorSubject(false);

  setloggedInUser(value: boolean) {
    this.loggedInUser = new BehaviorSubject(value);
  }
  getloggedInUser(): Observable<boolean> {
    return this.loggedInUser;
  }
}
