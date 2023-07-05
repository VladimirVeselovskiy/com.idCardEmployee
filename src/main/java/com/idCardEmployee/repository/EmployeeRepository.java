package com.idCardEmployee.repository;

import com.idCardEmployee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // создал свой метод, имя метода используется определенными шаблонами
    List<Employee> findAllBySurname(String surname);

}
