package com.idCardEmployee.entity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "department")
    private String department;
    @Column(name = "date_of_employment")
    private LocalDate dateOfEmployment;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_card_access")
    private CardAccess cardAccess;
    
    public Employee() {
    }

    public Employee(String surname, String department, LocalDate dateOfEmployment) {
        this.surname = surname;
        this.department = department;
        this.dateOfEmployment = dateOfEmployment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfReceiving) {
        this.dateOfEmployment = dateOfReceiving;
    }


    public CardAccess getCard() {
        return cardAccess;
    }

    public void setCard(CardAccess cardAccess) {
        this.cardAccess = cardAccess;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", department='" + department + '\'' +
                ", dateOfReceiving=" + dateOfEmployment +
                ", card=" + cardAccess +
                '}';
    }
}
