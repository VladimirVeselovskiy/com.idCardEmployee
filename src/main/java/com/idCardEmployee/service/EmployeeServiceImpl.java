package com.idCardEmployee.service;
import com.idCardEmployee.exception.CardAccessNotFoundException;
import com.idCardEmployee.exception.EmployeeNotFoundException;
import com.idCardEmployee.repository.EmployeeRepository;
import com.idCardEmployee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
    @Override
    public Employee updateEmployee(Employee employee){
        Optional<Employee> updateEmployee = employeeRepository.findById(employee.getId());
        if(updateEmployee.isEmpty()){
            throw new EmployeeNotFoundException(employee.getId());
        }
        if (employee.getCard().getId() != updateEmployee.get().getCard().getId()){
            throw new CardAccessNotFoundException("The employee ID card was not found, cardId= ", employee.getCard().getId());
        }
       else {
            Employee emp = updateEmployee.get();
            emp.setSurname(employee.getSurname());
            emp.setDepartment(employee.getDepartment());
            emp.setDateOfEmployment(employee.getDateOfEmployment());
            emp.setCard(employee.getCard());
            employeeRepository.save(emp);
            return emp;
        }
    }
    @Override
    public Employee getEmployee(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElseThrow(() ->new EmployeeNotFoundException(id));
    }
    @Override
    public void deleteEmployee(int id) {
        try {
            employeeRepository.deleteById(id);
        }catch (Exception e){
            throw new EmployeeNotFoundException(id);
        }
    }
    @Override
    public List<Employee> findAllBySurname(String surname) {
        return employeeRepository.findAllBySurname(surname);
    }
}
