package com.woniuxy.grades.exception;

public class GradeException extends RuntimeException{
    public GradeException(){}
    public GradeException(String message){
        super(message);
    }
}
