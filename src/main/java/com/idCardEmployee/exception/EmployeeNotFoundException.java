package com.idCardEmployee.exception;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(int id){
        super("Employee is not found, id= " + id);
    }

}
