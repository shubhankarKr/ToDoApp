import { Component, OnInit } from '@angular/core';
import { TasKListComponent } from './task-list/task-list.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'ToDoList';
  constructor(private router:Router){}

  filter(inputString:string){
  if(inputString){
    this.router.navigate(['/home'],{queryParams:{'searchBy':inputString}})
  }else{
    this.router.navigate(['/home'])
  }
  }
}
