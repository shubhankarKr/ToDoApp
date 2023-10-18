import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToDoService } from '../service/to-do.service';
import { User } from '../model/User';
import { getCookie, setCookie } from 'typescript-cookie'
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  loginForm!:FormGroup
  errorMessage!:string
  constructor(private fb:FormBuilder,private service:ToDoService,private router:Router){}
  currentUser=new User();
  ngOnInit(): void {
    this.loginForm=this.fb.group({
      username : ['',Validators.required],
      password :['',Validators.required]
    });
  }
  loginUser(){
    const formData = new FormData();
    formData.append('username',this.loginForm.get('username')?.value);
    formData.append('password',this.loginForm.get('password')?.value);
    this.currentUser.userName=this.loginForm.get('username')?.value;
    this.currentUser.password=this.loginForm.get('password')?.value;
   this.service.userLogin(this.currentUser).subscribe({
    next : responseData=>{
      this.currentUser=responseData
      console.log(' current User '+this.currentUser);
      let xsrf=getCookie('XSRF-TOKEN')
      window.sessionStorage.setItem('userdetails',JSON.stringify(this.currentUser));
      window.sessionStorage.setItem('XSRF-TOKEN',JSON.stringify(xsrf));
      console.log('userDetails loginUser '+sessionStorage.getItem('userdetails'))
      console.log('userDetails loginUser parse '+JSON.parse(sessionStorage.getItem('userdetails')!))
      console.log('userDetails loginUser XSRF-TOKEN '+sessionStorage.getItem('XSRF-TOKEN'))
      this.router.navigate(['/']);
    },
    error : err =>{
      console.log(err);
    }
   })
   
  }

    // this.service.userLogin(data).subscribe({
    //   next :res=> {
    //     this.response=res 
    //     this.router.navigate(['/home'])
    //   }
    // });
}
