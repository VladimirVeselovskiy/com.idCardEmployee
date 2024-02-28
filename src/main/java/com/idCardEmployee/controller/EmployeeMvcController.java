package com.idCardEmployee.controller;

import com.idCardEmployee.entity.Employee;
import com.idCardEmployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/id-card-employee")
public class EmployeeMvcController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employees")
    public String showAllEmployee(Model model){
        List<Employee> employeeList = employeeService.getAllEmployee();
        model.addAttribute("allEmployees", employeeList);

        return "all-employees";
    }

    @RequestMapping("/employees/addNewEmployee")
    public String addNewEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "add-employee";
    }

    @RequestMapping("/employees/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);

        return "redirect:/id-card-employee/employees";
    }

    @RequestMapping("/employees/updateEmployee")
    public String updateEmployee(@RequestParam("empId") int id, Model model){
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);

        return "update-employee";
    }

    @RequestMapping(value = "/employees/update-employee")
    public String updateEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.updateEmployee(employee);

        return "redirect:/id-card-employee/employees";
    }

    @RequestMapping("/employees/deleteEmployee")
    public String deleteEmployee(@RequestParam("empId") int id){
        employeeService.deleteEmployee(id);

        return "redirect:/id-card-employee/employees";
    }
}
