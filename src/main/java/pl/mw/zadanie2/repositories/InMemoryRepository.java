package pl.mw.zadanie2.repositories;

import org.springframework.stereotype.Repository;
import pl.mw.zadanie2.model.Employee;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Class implementing GeneralRepository Interface
 * used to manipulate objects in database
 *
 * @author Michał Wojda
 * @version alpha
 * @see pl.mw.zadanie2.repositories.GeneralRepository
 */
@Repository
@Transactional
public class InMemoryRepository implements GeneralRepository<Employee, Long> {

    /**
     * List holding Employee class objects - mock database
     *
     * @see pl.mw.zadanie2.model.Employee
     */
    private List<Employee> employeesList;
    /**
     * Variable used for id generation - thread safe
     */
    private static AtomicLong idCounter = new AtomicLong();

    /**
     * Constructor
     */
    public InMemoryRepository() {
        this.employeesList = new ArrayList<>();
    }

    /**
     * Gets one employee
     *
     * @param id of the employee that we want to get
     * @return Employee type object or null if not present
     */
    @Override
    public Employee get(Long id) {
        return employeesList.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);

    }

    /**
     * Return list of employees
     *
     * @return List of Employee type objects
     */
    @Override
    public List<Employee> getAll() {
        return employeesList;
    }

    /**
     * Adds employee into database
     *
     * @param employee object that is to be added into database
     */
    @Override
    public void add(Employee employee) {

        employee.setId(generateId());
        employeesList.add(employee);

    }

    /**
     * Delete employee from database
     *
     * @param id of the employee that is to be deleted
     */
    @Override
    public void delete(Long id) {
        employeesList = employeesList.stream().filter(e -> !(e.getId().equals(id))).collect(Collectors.toList());
    }

    /**
     * Update employee in database
     *
     * @param employee object to be updated
     */
    @Override
    public void update(Employee employee) {
        employeesList.remove(this.get(employee.getId()));
        employeesList.add(employee);
    }

    public List<Employee> getEmployeeList() {
        return employeesList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeesList = employeeList;
    }

    public static Long generateId() {
        return idCounter.getAndIncrement();
    }
}
