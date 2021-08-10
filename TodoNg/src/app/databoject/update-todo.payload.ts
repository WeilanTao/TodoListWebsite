export class UpdateTodoPayload{
    constructor(
    public todoId: number,
    public todoName: string,
    public description: string,
    public date: Date,
    public isDone: boolean

    ){

    }
}