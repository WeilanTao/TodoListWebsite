package com.taoemily.MyTodo;

public class AuthenticationBean {
    String message;

    public AuthenticationBean(String input){
        this.message=input;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return String.format("AuthBean is working [messabe=%s]",message);
    }
}
