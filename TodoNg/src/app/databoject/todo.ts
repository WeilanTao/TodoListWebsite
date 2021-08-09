export class Todo{
    constructor(
      public todo_id:number,
      public todoName:string,
      public description:string, 
      public isDone:boolean,
      public date:Date,
  
    ){
    }
  
  }