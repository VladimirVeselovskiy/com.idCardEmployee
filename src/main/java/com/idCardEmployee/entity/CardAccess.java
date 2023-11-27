package com.idCardEmployee.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "card_access")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class CardAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_access_id")
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "level_access")
    private LevelAccess levelAccess;
    @OneToOne(mappedBy = "cardAccess")
    private Employee employee;

    public CardAccess() {
    }
    public CardAccess(int id, LevelAccess levelAccess) {
        this.id = id;
        this.levelAccess = levelAccess;
    }
    public CardAccess(int id, LevelAccess levelAccess, Employee employee) {
        this.id = id;
        this.levelAccess = levelAccess;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LevelAccess getLevelAccess() {
        return levelAccess;
    }

    public void setLevelAccess(LevelAccess levelAccess) {
        this.levelAccess = levelAccess;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "CardAccess{" +
                "id=" + id +
                ", levelAccess=" + levelAccess +
                ", employee=" + employee +
                '}';
    }
}
