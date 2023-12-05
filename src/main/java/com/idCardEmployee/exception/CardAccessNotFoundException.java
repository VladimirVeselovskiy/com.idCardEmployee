package com.idCardEmployee.exception;

public class CardAccessNotFoundException extends RuntimeException{
    public CardAccessNotFoundException(int id){
        super("CardAccess is not found, id= " + id);
    }

    public CardAccessNotFoundException(String message, int id) {
        super(message + id);
    }

}
