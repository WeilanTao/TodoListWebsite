export class UpdateTodoPayload{
    constructor(
    public todo_id: number,
    public name: string,
    public description: string,
    public date: Date,
    public isDone: boolean

    ){

    }
}