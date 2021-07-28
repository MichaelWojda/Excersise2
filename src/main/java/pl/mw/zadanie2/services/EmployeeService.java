package pl.mw.zadanie2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mw.zadanie2.model.Employee;
import pl.mw.zadanie2.repositories.InMemoryRepository;

import java.util.List;

/**
 * Service class holding methods
 * for employee related operations
 *
 * @author Michał Wojda
 * @version alpha
 */
@Service
public class EmployeeService {

    /**
     * Repository
     *
     * @see pl.mw.zadanie2.repositories.InMemoryRepository
     */
    private final InMemoryRepository inMemoryRepository;

    /**
     * Constructor
     */
    @Autowired
    public EmployeeService(InMemoryRepository inMemoryRepository) {
        this.inMemoryRepository = inMemoryRepository;
    }

    /**
     * Return list of employees
     *
     * @return List of Employee type objects
     */
    public List<Employee> getAllEmployees() {
        return inMemoryRepository.getAll();
    }

    /**
     * Gets one employee
     *
     * @param id of the searched employee
     * @return Employee type object or null if not present
     */
    public Employee getEmployeeById(Long id) {
        return inMemoryRepository.get(id);
    }

    /**
     * Adds employee into database
     * Check if employee is null or already exists in database, throws IllegalArgumentException
     * and raises 'false' flag otherwise adds employee into database
     * and returns true
     *
     * @param employee object that is to be added into database
     * @return false is employee is null or already exists or true otherwise
     */
    public boolean addEmployeeToDataBase(Employee employee) {

        try {
            if (employee == null) {
                throw new IllegalArgumentException("Error!Employee is null");
            } else if (inMemoryRepository.getEmployeeList().contains(employee)) {

                throw new IllegalArgumentException("Error!Employee already exists");

            } else {
                inMemoryRepository.add(employee);
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Delete employee from database
     * Checks if employee is null, throws IllegalArgumentException
     * and raises 'false' flag otherwise adds employee into database
     * and returns true
     *
     * @param id of the employee that is to be deleted
     * @return false is employee is null or true otherwise
     */
    public boolean deleteEmployeeFromDB(Long id) {
        try {
            if (inMemoryRepository.get(id) == null) {
                throw new IllegalArgumentException("Error!Employee with id number:" + id + " does not exist!");
            } else {
                inMemoryRepository.delete(id);
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Updates employee in database
     * Checks if employee is null, throws IllegalArgumentException
     * and raises 'false' flag otherwise adds employee into database
     * and returns true
     *
     * @param employee object to be updated
     * @return false is employee is null or true otherwise
     */
    public boolean updateEmployeeInDB(Employee employee) {
        try {
            if (inMemoryRepository.get(employee.getId()) == null) {
                throw new IllegalArgumentException("Error!Employee is null");
            } else {
                inMemoryRepository.update(employee);
                return true;
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
