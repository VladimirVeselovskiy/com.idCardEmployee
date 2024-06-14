package com.idCardEmployee.controller.mvc;

import com.idCardEmployee.entity.Employee;
import com.idCardEmployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/id-card-employee")
public class EmployeeMvcController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public String showAllEmployee(Model model){
        List<Employee> employeeList = employeeService.getAllEmployee();
        model.addAttribute("allEmployees", employeeList);

        return "employee/all_employees";
    }

    @GetMapping("/employees/addNewEmployee")
    public String addNewEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "employee/add_employee";
    }

    @PostMapping("/employees/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);

        return "redirect:/id-card-employee/employees";
    }

    @GetMapping("/employees/update-employee/{id}")
    public String getAndUpdateEmployee(@PathVariable int id, Model model){
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);

        return "employee/update_employee";
    }

    @PostMapping(value = "/employees/update-employee")
    public String updateEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.updateEmployee(employee);

        return "redirect:/id-card-employee/employees";
    }

    @GetMapping("/employees/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);

        return "redirect:/id-card-employee/employees";
    }

    @GetMapping("/employees/surname")
    public String searchEmployeeBySurname(@RequestParam("surname") String surname, Model model){
        List<Employee> employeeListBySurname = employeeService.findAllBySurname(surname);
        model.addAttribute("employee", employeeListBySurname);

        return "employee/employees_by_surname";
    }
}
