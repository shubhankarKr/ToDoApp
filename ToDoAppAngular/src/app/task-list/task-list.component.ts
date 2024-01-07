import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ToDoService } from '../service/to-do.service';
import { toDoTaskModel } from '../model/ToDoTaskModel';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-tash-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TasKListComponent implements OnInit {
  loggedIn!:boolean;

  constructor(private service: ToDoService, private activatedRoute: ActivatedRoute, private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getloggedInUser().subscribe({
      next: res => {
        this.loggedIn=res
        if (res == false) {
          this.router.navigate(['/login'])
        } else {
          this.activatedRoute.queryParams.subscribe({
            next: res => {
              if (res['searchBy']) {
                this.filterTaskList(res['searchBy'])
              } else {
                this.getTaskList();
              }
            }
          })
          this.router.navigate(['/home'])
        }
      },
      error:err=>console.log(' errro in taskList component '+JSON.stringify(err))
      
    })

  }

  taskList: toDoTaskModel[] = []

  getTaskList() {
    this.service.getAllTask().subscribe({
      next: res => {
        this.taskList = res;
      }
    });
  }

  deleteTask(id: number) {
    this.taskList = [];
    this.service.deleteTask(id).subscribe({
      next: res => {
        this.getTaskList()
      }
    });
  }

  filterTaskList(data: string) {
    this.service.searchTask(data).subscribe({
      next: res => {
        this.taskList = res;
      }
    });
  }
}
