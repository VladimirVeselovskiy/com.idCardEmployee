package com.idCardEmployee.controller.mvc;

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

        return "all_employees";
    }

    @RequestMapping("/employees/addNewEmployee")
    public String addNewEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "add_employee";
    }

    @RequestMapping("/employees/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);

        return "redirect:/id-card-employee/employees";
    }

    @RequestMapping("/employees/update-employee")
    public String getAndUpdateEmployee(@RequestParam("empId") int id, Model model){
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);

        return "update_employee";
    }

    @RequestMapping(value = "/employees/updateEmployee")
    public String updateEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.updateEmployee(employee);

        return "redirect:/id-card-employee/employees";
    }

    @RequestMapping("/employees/deleteEmployee")
    public String deleteEmployee(@RequestParam("empId") int id){
        employeeService.deleteEmployee(id);

        return "redirect:/id-card-employee/employees";
    }

    @RequestMapping("/employees/surname")
    public String searchEmployeeBySurname(@RequestParam("surname") String surname, Model model){
        List<Employee> employeeListBySurname = employeeService.findAllBySurname(surname);
        model.addAttribute("employee", employeeListBySurname);

        return "employees_by_surname";
    }
}
