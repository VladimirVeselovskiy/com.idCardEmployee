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
    public String ShowAllEmployee(Model model){

        List<Employee> employeeList = employeeService.getAllEmployee();
        model.addAttribute("allEmployees", employeeList);

        return "all-employees";
    }

    //TODO надо исправить. не сохраняет при добавление карты сотруднику
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

    //TODO надо исправить, не работает если есть карта у сотрудника
    @RequestMapping("/employees/updateEmployee")
    public String updateEmployee(@RequestParam("empId") int id, Model model){

        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);

        return "add-employee";
    }

    @RequestMapping("/employees/deleteEmployee")
    public String deleteEmployee(@RequestParam("empId") int id){
        employeeService.deleteEmployee(id);

        return "redirect:/id-card-employee/employees";
    }
}
