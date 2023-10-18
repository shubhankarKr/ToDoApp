import { HttpClient, HttpErrorResponse,HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, retry, tap, throwError } from 'rxjs';
import { toDoTaskModel } from '../model/ToDoTaskModel';
import { User } from '../model/User';

@Injectable({
  providedIn: 'root'
})
export class ToDoService {

  constructor(private http:HttpClient) { }

  getAllTask():Observable<toDoTaskModel[]>{
    return this.http.get<toDoTaskModel[]>('http://localhost:8000/task/getAllTasks',{withCredentials:true})
    .pipe(catchError(this.handleError) ) ;
  }

  updateTask(task : any):Observable<toDoTaskModel>{
    console.log('update service '+JSON.stringify(task));
    
    return this.http.put<toDoTaskModel>('http://localhost:8000/task/update',task,{withCredentials:true}).pipe(
      catchError(this.handleError)
    );
  }

  deleteTask(id : number):Observable<boolean>{
    let url = `http://localhost:8000/task/delete/${id}`;
    return this.http.delete<boolean>(url).pipe(
      catchError(this.handleError)
    );
  }

  addTask(task : any):Observable<toDoTaskModel>{
    return this.http.post<toDoTaskModel>('http://localhost:8000/task/add',task,{withCredentials:true}).pipe(
      catchError(this.handleError)
    );
  }

  searchTask(inputString:string):Observable<toDoTaskModel[]>{
    let url = `http://localhost:8000/task/search/${inputString}`;
    return this.http.get<toDoTaskModel[]>(url,{withCredentials:true})
    .pipe(catchError(this.handleError) ) ;
  }

  getTaskById(id:number):Observable<toDoTaskModel>{
    let url = `http://localhost:8000/task/${id}`;
    return this.http.get<toDoTaskModel>(url,{withCredentials:true})
    .pipe(catchError(this.handleError) ) ;
  }

  getColorData():Observable<any[]>{
    let url = `http://localhost:8000/task/getColorMD`;
    return this.http.get<toDoTaskModel>(url,{withCredentials:true})
    .pipe(catchError(this.handleError) ) ;
  }
  updateColor(data:any):Observable<any>{
    let url=`http://localhost:8000/task/updateColor`;
    return this.http.put<any>(url,data,{withCredentials:true}).pipe(
      catchError(this.handleError)
    )
  }

  registerUser(data:any):Observable<any>{
    let url=`http://localhost:8000/user/register`;
    return this.http.post<any>(url,data).pipe(
      catchError(this.handleError)
    )
  }

  userLogin(user:User){
    console.log('user login called');
    
    let url=`http://localhost:8000/user/login`;
    let httpHeaders = new HttpHeaders();
     httpHeaders = httpHeaders.append('Authorization', 'Basic ' + window.btoa(user.userName + ':' + user.password));
    console.log('Authorization service '+httpHeaders.get('Authorization'));
    return this.http.get<User>(url,{headers:httpHeaders,withCredentials:true}).pipe(
      catchError(this.handleError)
    )
  }

  private handleError(err: HttpErrorResponse ): Observable<any> {
    console.log(err);
    if(err.message){
      return throwError( () => {
        return err.message;
       } );
    }else{
      return throwError( () => {
        return 'Error Has occured in APIService';
       } );
    }
  }
}
