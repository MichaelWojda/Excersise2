package pl.mw.zadanie2.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.mw.zadanie2.model.Employee;
import pl.mw.zadanie2.repositories.InMemoryRepository;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private InMemoryRepository inMemoryRepository = new InMemoryRepository();
    private EmployeeService employeeService = new EmployeeService(inMemoryRepository);


    @Test
    void getAllEmployees() {
        Random random = new Random();
        int number = random.nextInt(10);
        for (int i = 0; i <= number; i++) {
            employeeService.addEmployeeToDataBase(new Employee("employee" + i, "employee" + i, "employee" + i, Employee.employeeStatus.STANDARD, i, i));
        }
        Assertions.assertEquals(employeeService.getAllEmployees().size() - 1, number);


    }


    @Test
    void addEmployeeToDataBase() {
        assertFalse(employeeService.addEmployeeToDataBase(null));
        assertTrue(employeeService.addEmployeeToDataBase(new Employee("employee", "employee", "employee", Employee.employeeStatus.STANDARD, 1, 1)));
    }

    @Test
    void deleteEmployeeFromDB() {

    }

    @Test
    void updateEmployeeInDB() {
    }
}