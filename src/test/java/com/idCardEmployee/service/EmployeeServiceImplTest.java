package com.idCardEmployee.service;

import com.idCardEmployee.entity.CardAccess;
import com.idCardEmployee.entity.Department;
import com.idCardEmployee.entity.Employee;
import com.idCardEmployee.entity.LevelAccess;
import com.idCardEmployee.exception.EmployeeNotFoundException;
import com.idCardEmployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;
    @Test
    void shouldGetAllEmployee(){
        //given
        Employee employee1 = new Employee(1,"Simons", Department.IT,
                LocalDate.of(1991,2,5), new CardAccess(1, LevelAccess.THREE));
        Employee employee2 = new Employee(2,"White", Department.Management,
                LocalDate.of(2001,3,8), new CardAccess(2,LevelAccess.TWO));

        given(employeeRepository.findAll()).willReturn(List.of(employee1, employee2));

        //when
        List<Employee> employeeList = employeeService.getAllEmployee();

        //then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
        verify(employeeRepository, times(1)).findAll();
    }
    @Test
    void shouldSaveEmployee(){
        //given
        Employee employee = new Employee(1,"Simons", Department.IT,
                LocalDate.of(1991,2,5), new CardAccess(1, LevelAccess.THREE));

        given(employeeRepository.save(employee)).willReturn(employee);

        //when
        employeeService.saveEmployee(employee);

        //then
        verify(employeeRepository, times(1)).save(employee);
    }
    @Test
    void shouldGetEmployeeById(){
        //given
        Employee employee = new Employee(1,"Simons", Department.IT,
                LocalDate.of(1991,2,5), new CardAccess(1, LevelAccess.THREE));

        given(employeeRepository.findById(1)).willReturn(Optional.of(employee));

        //when
        int employeeById = employeeService.getEmployee(employee.getId()).getId();

        //then
        assertThat(employeeById).isNotNull();
        verify(employeeRepository, times(1)).findById(employeeById);
    }
    @Test
    void  shouldEmployeeNotFoundException_WhenGetEmployeeById() throws EmployeeNotFoundException{
        //given
        int employeeId = 1;

        given(employeeRepository.findById(employeeId)).willReturn(Optional.empty());

        //when
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployee(employeeId));

        //then
        verify(employeeRepository, times(1)).findById(employeeId);
    }
    @Test
    void shouldUpdateEmployee(){
        //given
        Employee employee = new Employee(1,"Simons", Department.IT,
                LocalDate.of(1991,2,5), new CardAccess(1, LevelAccess.THREE));

        given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));
        employee.setSurname("Bishop");
        employee.setDepartment(Department.Logistics);
        employee.setDateOfEmployment(LocalDate.of(1991, 3,21));

        //when
        Employee updateEmployee = employeeService.updateEmployee(employee);

        //then
        assertNotNull(updateEmployee);
        verify(employeeRepository, times(1)).findById(employee.getId());
        verify(employeeRepository, times(1)).save(updateEmployee);
    }
    @Test
    void shouldEmployeeNotFoundException_WhenUpdateEmployee(){
        //given
        Employee employeeId = new Employee();

        given(employeeRepository.findById(employeeId.getId())).willReturn(Optional.empty());

        //when
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.updateEmployee(employeeId));

        //then
        verify(employeeRepository, times(1)).findById(employeeId.getId());
    }
    @Test
    void shouldDeleteEmployeeById(){
        //given
        int employeeId = 1;

        willDoNothing().given(employeeRepository).deleteById(employeeId);

        //when
        employeeService.deleteEmployee(employeeId);

        //then
        verify(employeeRepository, times(1)).deleteById(employeeId);
    }
    @Test
    void shouldEmployeeNotFoundException_WhenDeleteEmployeeById(){
        //given
        int employeeId = 1;

        doThrow(EmployeeNotFoundException.class).when(employeeRepository).deleteById(employeeId);

        //when
        try {
            employeeService.deleteEmployee(employeeId);
        }catch (EmployeeNotFoundException e){
            assertEquals("Employee is not found, id= " + employeeId, e.getMessage());
        }

        //then
        verify(employeeRepository, times(1)).deleteById(employeeId);
    }
    @Test
    void shouldGetEmployeeFindBySurname(){
        //given
        List<Employee> employeeList = new ArrayList<>();
        Employee employee1 = new Employee(1,"Simons", Department.IT,
                LocalDate.of(1991,2,5), new CardAccess(1, LevelAccess.THREE));
        Employee employee2 = new Employee(2,"White", Department.HR,
                LocalDate.of(2001,3,8), new CardAccess(2,LevelAccess.TWO));
        employeeList.add(employee1);
        employeeList.add(employee2);

        given(employeeRepository.findAllBySurname(employee1.getSurname())).willReturn(employeeList);

        //when
        employeeService.findAllBySurname(employee1.getSurname());

        //then
        verify(employeeRepository, times(1)).findAllBySurname(employee1.getSurname());
    }
}
