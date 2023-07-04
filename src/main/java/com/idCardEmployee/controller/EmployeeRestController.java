package com.idCardEmployee.controller;

import com.idCardEmployee.entity.Employee;
import com.idCardEmployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("employees")
    public List<Employee> showAllEmployees(){
        List<Employee> allEmployees = employeeService.getAllEmployee();
        return allEmployees;
    }

}
