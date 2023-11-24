package com.idCardEmployee.controller;

import com.idCardEmployee.entity.Employee;
import com.idCardEmployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/employees")
    public List<Employee> showAllEmployees(){
        return employeeService.getAllEmployee();
    }
    @GetMapping("/employees/{id}")
    public Employee showEmployeeById(@PathVariable int id){
        return employeeService.getEmployee(id);
    }
    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addNewEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }
    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
        return employee;
    }
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
        return "Сотрудник с id: " + id + " удален";
    }
    @GetMapping("/employees/surname/{surname}")
    public List<Employee> showAllEmployeesBySurname(@PathVariable String surname){
        return employeeService.findAllBySurname(surname);
    }
}
