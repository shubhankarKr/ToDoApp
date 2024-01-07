import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToDoService } from '../service/to-do.service';
import { User } from '../model/User';
import { getCookie, setCookie } from 'typescript-cookie'
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  loginForm!:FormGroup
  message='Please login to continue'
  constructor(private fb:FormBuilder,private service:ToDoService,private router:Router,
    private userService:UserService){}
  currentUser=new User();
  ngOnInit(): void {
    this.loginForm=this.fb.group({
      username : ['',Validators.required],
      password :['',Validators.required]
    });
  }
  loginUser(){
    this.currentUser.userName=this.loginForm.get('username')?.value;
    this.currentUser.password=this.loginForm.get('password')?.value;
    this.userService.setloggedInUser(false);
   this.service.userLogin(this.currentUser).subscribe({
    next : responseData=>{
      this.currentUser=responseData
      let xsrf=getCookie('XSRF-TOKEN')
      window.sessionStorage.setItem('XSRF-TOKEN',JSON.stringify(xsrf));
      this.userService.setloggedInUser(true);
      this.router.navigate(['/']);
    },
    error : err =>{
      // console.log(err);
      this.message=err+' Please check credentials';
    }
   })
   
  }
}
