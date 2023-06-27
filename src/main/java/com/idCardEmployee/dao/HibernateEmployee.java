package com.idCardEmployee.dao;

import com.idCardEmployee.entity.CardAccess;
import com.idCardEmployee.entity.Employee;
import com.idCardEmployee.entity.LevelAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.List;

public class HibernateEmployee {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(CardAccess.class)
                .buildSessionFactory();

        try (sessionFactory; Session session = sessionFactory.getCurrentSession() )
        {
            Employee employee = new Employee("Snow", "Steering committee",
                    LocalDate.of(1994, 8, 1));

            CardAccess cardAccess = new CardAccess(LevelAccess.THREE);
            employee.setCard(cardAccess);

            session.beginTransaction();

//            session.save(employee);
//            session.getTransaction().commit();

            List<Employee> emps = session.createQuery("from Employee " +
                    "where surname = 'Simons'")
                    .getResultList();
            for (Employee e : emps)
                System.out.println(e);
            session.getTransaction().commit();

//            Employee emp = session.get(Employee.class,1);
//            emp.setSurname("White");
//            session.getTransaction().commit();

//            Employee emp = session.get(Employee.class,1);
//            session.delete(emp);
//            session.getTransaction().commit();


        }
    }
}
