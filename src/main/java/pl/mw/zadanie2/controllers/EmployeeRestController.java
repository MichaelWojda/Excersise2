package pl.mw.zadanie2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.mw.zadanie2.model.Employee;
import pl.mw.zadanie2.services.EmployeeService;

import java.util.List;

/**
 * Controller class holding methods
 * for employee related HTTP requests
 *
 * @author Michał Wojda
 * @version alpha
 */
@RestController
@RequestMapping("/employee")
public class EmployeeRestController {


    /**
     * Employee Service
     *
     * @see pl.mw.zadanie2.services.EmployeeService
     */
    private EmployeeService employeeService;

    /**
     * Constructor
     */
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Accepts GET HTTP Request and returns List of Employees
     *
     * @return List of Employee's
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();

    }

    /**
     * Accepts GET HTTP Request with id param and returns Employee object
     *
     * @param id of the employee that we want to get
     * @return Employee class object or null if does not exist
     */
    @GetMapping(path = "/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    /**
     * Accepts POST HTTP Request with Employee param and adds it into Data Base
     * Prints information if operation was successful or not.
     *
     * @param employee to be added into database
     */
    @PostMapping
    public void addEmployeeToDB(@RequestBody Employee employee) {
        if (employeeService.addEmployeeToDataBase(employee)) {
            System.out.println("Employee added successfully");
        } else {
            System.err.println("Employee has not been added");
        }
    }

    /**
     * Accepts PUT HTTP Request with Employee param and updates it in Data Base
     * Prints information if operation was successful or not.
     *
     * @param employee to be updated in database
     */
    @PutMapping
    public void updateEmployee(@RequestBody Employee employee) {
        if (employeeService.updateEmployeeInDB(employee)) {
            System.out.println("Employee updated successfully");
        } else {
            System.err.println("Employee has not been updated");
        }
    }

    /**
     * Accepts DELETE HTTP Request with id param and deletes Employee
     * with this id from Data Base
     * Prints information if operation was successful or not.
     *
     * @param id of the employee that is to be deleted from database
     */
    @DeleteMapping(path = "/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployeeFromDB(id)) {
            System.out.println("Employee deleted successfully");
        } else {
            System.err.println("Employee has not been deleted");
        }
    }
}
