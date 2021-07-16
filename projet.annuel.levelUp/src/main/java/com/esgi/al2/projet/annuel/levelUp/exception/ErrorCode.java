package com.esgi.al2.projet.annuel.levelUp.exception;

public enum ErrorCode {
    USER_NOT_FOUND(1000),
    USER_NOT_VALID(1001),
    USER_ALREADY_EXISTS(1002),
    EXERCISE_NOT_FOUND(2000),
    EXERCISE_NOT_VALID(2001),
    EXERCISE_ALREADY_EXISTS(2002);

    private int code;

    ErrorCode(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
