export class User{
    userId!:number
    role!:string
    userName!:string
    password!:string
    authStatus!:boolean

    User(userId?:number ,role?:string,userName?:string,password?:string,authStatus?:boolean){
        this.role = role || ''
        this.userName=userName || ''
        this.password=password ||''
        this.authStatus=authStatus || false
        this.userId=userId || -1
    }
}