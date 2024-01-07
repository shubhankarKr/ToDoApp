import { Component, OnInit } from '@angular/core';
import { TasKListComponent } from './task-list/task-list.component';
import { Router } from '@angular/router';
import { Cookies, setCookie } from 'typescript-cookie';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserService } from './service/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})


export class AppComponent implements OnInit {

  title = 'ToDoList';
  loggedIn!:boolean
  constructor(private router: Router,private loggedInService:UserService) { }
  ngOnInit(): void {
    this.loggedInService.getloggedInUser().subscribe({
      next:res=>{
        // console.log(' app componet subscribe');
          if(res == true){
            this.loggedIn=true;
          }else{
            this.loggedIn=false;
          }
      },
      error:err=>{
        // console.log(err);
        
      }
    })
  }

  filter(inputString: string) {
    if (inputString) {
      this.router.navigate(['/home'], { queryParams: { 'searchBy': inputString } })
    } else {
      this.router.navigate(['/home'])
    }
  }
  logout() {
    window.sessionStorage.setItem('XSRF-TOKEN', '');
    this.loggedInService.setloggedInUser(false)
    // this.router.navigate(['/login']);
  }
}
