export class toDoTaskModel{
    id!:number;
	createdDate!:Date;
    lastUpdatedDate!:Date;
	description!:string;
	title!:string;
	colour!:{colorCode:string,colorId:number}
}