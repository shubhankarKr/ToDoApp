import { Component, Inject } from '@angular/core';
import { ToDoService } from '../service/to-do.service';
import { FormBuilder, FormControl, FormGroup, Validators,AbstractControl } from '@angular/forms';
import { toDoTaskModel } from '../model/ToDoTaskModel';
import { ActivatedRoute, Router } from '@angular/router';
import { formatDate } from '@angular/common';
import { LOCALE_ID } from "@angular/core";


@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent {
  constructor(private service:ToDoService,private fb:FormBuilder,private router:Router, private activedRoute:ActivatedRoute,@Inject( LOCALE_ID ) localID: string){this.locale=localID}
  addTaskForm!:FormGroup
  response!:toDoTaskModel;
  public locale: string;
  toToListArray:toDoTaskModel[]=[];
  mode!:string

  ngOnInit(){

    
    if(/\d$/.test(this.router.url)){
      this.mode='view';
    }else if(this.router.url.endsWith('edit')){
      this.mode="edit"
    }else{
      this.mode='add'
    }
    console.log('mode value '+this.mode);

     this.addTaskForm=this.fb.group({
      title : [{value :'',disabled : this.mode =='view'},Validators.required],
      description : [{value :'',disabled : this.mode =='view'}],
      lastUpdatedDate:[{value :'',disabled : this.mode !='add'}],
      id:[{value :'',disabled : this.mode == 'view'}],
      createdDate :[{value :'',disabled : this.mode != 'add'}],
      colorCode:[{value :'',disabled : this.mode != 'add'}],
    });
     
    if(this.mode == 'edit'){
      this.service.getTaskById(this.activedRoute.snapshot.params['id']).subscribe({
        next: (res)=>{
          this.response=res
          this.addTaskForm.controls['title'].setValue(res.title)
          this.addTaskForm.controls['description'].setValue(res.description)
          if(res.lastUpdatedDate){
            this.addTaskForm.controls['lastUpdatedDate'].setValue(formatDate(res.lastUpdatedDate,'dd-MM-yyyy, h:mm a',this.locale))
          }else{
            this.addTaskForm.controls['lastUpdatedDate'].setValue('NA')
          }
          this.addTaskForm.controls['createdDate'].setValue(formatDate(res.createdDate,'dd-MM-yyyy, h:mm a',this.locale))
      }  })
    }else if(this.mode =='view'){
      this.service.getTaskById(this.activedRoute.snapshot.params['id']).subscribe({
        next: (res)=>{
          this.response=res
          this.addTaskForm.controls['title'].setValue(res.title)
          this.addTaskForm.controls['id'].setValue(res.id)
          this.addTaskForm.controls['description'].setValue(res.description)
          this.addTaskForm.controls['lastUpdatedDate'].setValue(formatDate(res.lastUpdatedDate,'dd-MM-yyyy, h:mm a',this.locale))
          this.addTaskForm.controls['createdDate'].setValue(formatDate(res.createdDate,'dd-MM-yyyy, h:mm a',this.locale))
          this.addTaskForm.controls['colorCode'].setValue(res.colour.colorCode)
      }  })
    } 

  }

  saveTask(){
    let modelToSave={
      description:this.addTaskForm.get('description')?.value,
      title:this.addTaskForm.get('title')?.value,
      colourCode:this.addTaskForm.get('colourCode')?.value
    }
    this.service.addTask(modelToSave).subscribe({
      next :res=> {
        this.response=res 
        this.router.navigate(['/home'])
      }
    });
  }
  updateTask(){
    let modelToUpdate={
      id:this.activedRoute.snapshot.params['id'],
      description:this.addTaskForm.get('description')?.value,
      title:this.addTaskForm.get('title')?.value,
      colourCode:this.addTaskForm.get('colourCode')?.value,
    }
    this.service.updateTask(modelToUpdate).subscribe({
      next :(res) =>{
        this.router.navigate(['/home']);
      }
    })
  }
}
