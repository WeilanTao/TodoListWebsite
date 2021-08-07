import { ErrorHandler } from "@angular/core";
import { AppError } from "./app-error";

export class AppErrorHandler implements ErrorHandler {
    handleError(error: AppError) {
        alert("An unexpected error occurred");
        console.log(error);
    }
  }
  