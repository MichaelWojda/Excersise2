package pl.mw.zadanie2.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.mw.zadanie2.model.Employee;
import pl.mw.zadanie2.repositories.InMemoryRepository;

/**
 * Class inserting mock
 * on loading of SpringBoot
 *
 * @author Michał Wojda
 * @version alpha
 */
@Component
public class mockDataLoader implements CommandLineRunner {

    /**
     * Repository
     *
     * @see pl.mw.zadanie2.repositories.InMemoryRepository
     */
    private InMemoryRepository inMemoryRepository;

    /**
     * Constructor
     */
    @Autowired
    public mockDataLoader(InMemoryRepository inMemoryRepository) {
        this.inMemoryRepository = inMemoryRepository;
    }

    /**
     * Method that creates and inserts into database two mock employees
     */
    @Override
    public void run(String... args) throws Exception {
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();

        employee1.setName("Jan");
        employee1.setLastname("Kowalski");
        employee1.setPassword("password");
        employee1.setEmployeeStatus(Employee.employeeStatus.PERFORMER);
        employee1.setHolidaysUsed(20);
        employee1.setHolidaysAvailable(6);

        employee2.setName("Artur");
        employee2.setLastname("Król");
        employee2.setPassword("password");
        employee2.setEmployeeStatus(Employee.employeeStatus.SLACKER);
        employee2.setHolidaysUsed(6);
        employee2.setHolidaysAvailable(20);

        inMemoryRepository.add(employee1);
        inMemoryRepository.add(employee2);

        for (Employee employee : inMemoryRepository.getEmployeeList()) {
            System.out.println(employee);
        }


    }
}
