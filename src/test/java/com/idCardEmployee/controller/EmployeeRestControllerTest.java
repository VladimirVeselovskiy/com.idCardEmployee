package com.idCardEmployee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.idCardEmployee.Application;
import com.idCardEmployee.entity.CardAccess;
import com.idCardEmployee.entity.Employee;
import com.idCardEmployee.entity.LevelAccess;
import com.idCardEmployee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class EmployeeRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void shouldGetAllEmployeeTest() throws Exception{
        //given
        Employee employee1 = new Employee(1,"Simons","IT",
                LocalDate.of(1991,2,5), new CardAccess(1, LevelAccess.THREE));
        Employee employee2 = new Employee(2,"White","HR",
                LocalDate.of(2001,3,8), new CardAccess(2,LevelAccess.TWO));

        List<Employee> employeeList = new ArrayList<>(Arrays.asList(employee1, employee2));

        //when
        when(employeeService.getAllEmployee()).thenReturn(employeeList);

        mockMvc.perform(get("/api/employees"))
                //then
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(employeeList.size()))
                .andDo(print());

        verify(employeeService, times(1)).getAllEmployee();
    }
    @Test
    public void shouldGetEmployeeByIdTest() throws Exception{
        //given
        Employee employee = new Employee(1,"Simons","IT",
                LocalDate.of(1991,2,5), new CardAccess(1, LevelAccess.THREE));

        //when
        when(employeeService.getEmployee(1)).thenReturn(employee);

        mockMvc.perform(get("/api/employees/{id}", 1))
                //then
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(employeeService, times(1)).getEmployee(1);
    }
    @Test
    public void shouldAddNewEmployee() throws Exception {
        //given
        Employee employee = new Employee(1,"Simons","IT",
                LocalDate.of(1991,2,5), new CardAccess(1, LevelAccess.THREE));

        // с этим дата становится преемлемой для json, без этого не работает
        objectMapper.registerModule(new JavaTimeModule());
        // без этого может работать, меняется тело запроса даты
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        //when
        mockMvc.perform(post("/api/employees")
                //then
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andDo(print());
    }
    @Test
    public void shouldUpdateEmployee() throws Exception {
        //given
        Employee employee = new Employee(1,"Simons","IT",
                LocalDate.of(1991,2,5), new CardAccess(1, LevelAccess.THREE));

        // с этим дата становится преемлемой для json, без этого не работает
        objectMapper.registerModule(new JavaTimeModule());
        // без этого может работать, меняется тело запроса даты
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        //when
        when(employeeService.getEmployee(employee.getId())).thenReturn(employee);

        employee.setSurname("Bishop");
        employee.setDepartment("Logistics");
        employee.setDateOfEmployment(LocalDate.of(1991, 3,21));

        when(employeeService.updateEmployee(employee)).thenReturn(employee);

        mockMvc.perform(put("/api/employees/{id}", 1)
                //then
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void shouldDeleteEmployeeTest() throws Exception{
        //given
        Employee employee = new Employee(1,"Simons","IT",
                LocalDate.of(1991,2,5), new CardAccess(1, LevelAccess.THREE));

        //when
        when(employeeService.getEmployee(employee.getId())).thenReturn(employee);
        doNothing().when(employeeService).deleteEmployee(employee.getId());

        mockMvc.perform(delete("/api/employees/{id}", employee.getId()))
                //then
                .andExpect(status().isOk())
                .andDo(print());

        verify(employeeService, times(1)).deleteEmployee(employee.getId());
        verifyNoMoreInteractions(employeeService);
    }
    @Test
    public void shouldGetEmployeeFindBySurname() throws Exception{
        //given
        Employee employee1 = new Employee(1,"Simons","IT",
                LocalDate.of(1991,2,5), new CardAccess(1, LevelAccess.THREE));

        List<Employee> employeeList = new ArrayList<>(List.of(employee1));

        //when
        when(employeeService.findAllBySurname(employee1.getSurname())).thenReturn(employeeList);

        mockMvc.perform(get("/api/employees/surname/{surname}", "Simons"))
                //then
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        verify(employeeService, times(1)).findAllBySurname(employee1.getSurname());
    }
}
