export class CreateTodoPayload{
    constructor(
      public name:string,
      public description:string, 
      public isDone:boolean,
      public date:Date,
  
    ){
    }
  
}