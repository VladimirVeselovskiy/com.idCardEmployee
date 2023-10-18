package com.idCardEmployee.service;

import com.idCardEmployee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();
    void saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Employee getEmployee(int id);
    void deleteEmployee(int id);
    List<Employee> findAllBySurname(String surname);
}
