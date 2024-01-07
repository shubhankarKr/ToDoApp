import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToDoService } from '../service/to-do.service';

@Component({
  selector: 'app-sigh-up',
  templateUrl: './sigh-up.component.html',
  styleUrls: ['./sigh-up.component.css']
})
export class SighUpComponent implements OnInit{
  
  registerForm!:FormGroup
  constructor(private fb:FormBuilder,private service:ToDoService){}

  ngOnInit(): void {
    this.registerForm=this.fb.group({
      username : ['',Validators.required],
      password :['',Validators.required]
    });
  }
  errorMessage!:string;
  successMessage!:string;

  registerUser(){
    let username=this.registerForm.get('username')?.value;
    let password=this.registerForm.get('password')?.value;
    // console.log(' component value '+{'username':username,'password':password});
    
    this.service.registerUser({'userName':username,'password':password,'role':'USER'}).subscribe({
      next:res=>{
        // console.log(' registerUser '+JSON.stringify(res));
        this.successMessage=res.successMessage;
        this.errorMessage=''
      },
      error:err=>{
        // console.log(' error value ',err);
        this.errorMessage=err+' Please login';
        
      }
    })

  }

}
