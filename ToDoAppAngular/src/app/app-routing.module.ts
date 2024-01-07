import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TasKListComponent } from './task-list/task-list.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { LoginComponent } from './login/login.component';
import { authGuard } from './routeGuards/auth.guard';
import { SighUpComponent } from './sigh-up/sigh-up.component';
import { AppComponent } from './app.component';
const routes: Routes = [
  {path:'home',component: TasKListComponent},
  {path:'add',component:AddTaskComponent,canActivate:[authGuard]},
  {path:':id/edit',component:AddTaskComponent,canActivate:[authGuard]},
  {path:'task/:id',component:AddTaskComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:SighUpComponent},
  {path:'',redirectTo:'/home' ,pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
 
 }
