package com.idCardEmployee.service;

import com.idCardEmployee.dao.EmployeeDAO;
import com.idCardEmployee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeDAO employeeDAO;
    @Override
    @Transactional
    public List<Employee> getAllEmployee() {
        return employeeDAO.getAllEmployee();
    }

//    @Override
//    public void saveEmployee(Employee employee) {
//
//    }
//
//    @Override
//    public Employee getEmployee(int id) {
//        return null;
//    }
//
//    @Override
//    public void deleteEmployee(int id) {
//
//    }
}
