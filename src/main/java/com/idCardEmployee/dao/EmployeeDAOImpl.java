package com.idCardEmployee.dao;

import com.idCardEmployee.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
    @Autowired
    private EntityManager entityManager; // используется для спрингбута вместо sessionFactory
    @Override
    public List<Employee> getAllEmployee() {
        Session session = entityManager.unwrap(Session.class);

        Query<Employee> query = session.createQuery("from Employee ", Employee.class);
        List<Employee> allEmployee = query.getResultList();
        return allEmployee;
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
