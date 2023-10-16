import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ToDoService } from '../service/to-do.service';
import { toDoTaskModel } from '../model/ToDoTaskModel';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-tash-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TasKListComponent implements OnInit{
  
   constructor(private service:ToDoService, private activatedRoute:ActivatedRoute){}

  ngOnInit(): void {
      this.getColorData();
      this.activatedRoute.queryParams.subscribe({
        next:res=>{
          if(res['searchBy']){
            this.filterTaskList(res['searchBy'])
          }else{
            this.getTaskList();
          }
        }
      })
  }

  taskList:toDoTaskModel[]=[]
  colorData!:any[]
  getTaskList(){
    this.service.getAllTask().subscribe({
      next : res=>{
        this.taskList=res;       
      }
    });
  }
  updateColor(taskId:number,colorId:number){
    this.service.updateColor({'taskId':taskId,'colorId':colorId}).subscribe({
      next :res=>{
        this.getTaskList()
      }
    })

  }
  deleteTask(id:number){
    this.taskList=[];
    this.service.deleteTask(id).subscribe({
      next : res=> {
        this.getTaskList()
      }
    });
  }

  filterTaskList(data:string){
    this.service.searchTask(data).subscribe({
      next : res=>{
        this.taskList=res; 
      }
    });
  }

  getColorData(){
    // this.service.getColorData().subscribe({
    //   next: res=>{
    //     this.colorData=res
    //   }
    // })
  }
}
