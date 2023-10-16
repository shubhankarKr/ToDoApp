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
     // window.sessionStorage.setItem("Authorization",responseData.headers.get('Authorization')!);
      // this.model = <any> responseData.body;
      console.log(' current User '+this.currentUser);

      let xsrf=getCookie('XSRF-TOKEN')
      
      window.sessionStorage.setItem('token',JSON.stringify(xsrf));
      // this.model.authStatus = 'AUTH';
      // window.sessionStorage.setItem("userdetails",JSON.stringify(this.model));
      // let xsrf = getCookie('XSRF-TOKEN')!;
      // window.sessionStorage.setItem("XSRF-TOKEN",xsrf);
      // this.router.navigate(['dashboard']);
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
