package pl.mw.zadanie2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mw.zadanie2.model.Employee;
import pl.mw.zadanie2.model.HolidayRequest;
import pl.mw.zadanie2.model.HolidayStatus;
import pl.mw.zadanie2.repositories.InMemoryRepository;

/**
 * Service class holding methods
 * for holidays requests
 *
 * @author Michał Wojda
 * @version alpha
 */
@Service
public class HolidayService {

    /**
     * Repository
     *
     * @see pl.mw.zadanie2.repositories.InMemoryRepository
     */
    private InMemoryRepository inMemoryRepository;
    /**
     * Service for sending email notifications
     *
     * @see pl.mw.zadanie2.services.MailService
     */
    private MailService mailService;

    /**
     * Constructor
     */
    @Autowired
    public HolidayService(InMemoryRepository inMemoryRepository, MailService mailService) {
        this.inMemoryRepository = inMemoryRepository;
        this.mailService = mailService;
    }

    /**
     * Empty Constructor
     */
    public HolidayService() {

    }

    /**
     * Method implying business logic for holiday requests
     * Gets id od the employee and number of requested holidays from
     * holiday request object, checks employeeStatus of the employee and
     * based on that:
     * If employee is PERFORMER can request for any number of holidays
     * If employee is SLACKER cannot request for any days of holidays
     * Other employees can only request for available days
     * Application also sends email notification if request passed approval
     *
     * @param holidayRequest object containing id of the employee and number of holidays requested
     */
    public boolean requestForHolidays(HolidayRequest holidayRequest) {
        try {
            Employee requestingEmployee = inMemoryRepository.get(holidayRequest.getEmployeeId());
            if (requestingEmployee == null) {
                throw new IllegalArgumentException("Error!Employee is null");
            }

            int daysRequested = holidayRequest.getDaysRequested();
            int totalDaysRequested = requestingEmployee.getHolidaysUsed() + daysRequested;
            int daysAvailable = requestingEmployee.getHolidaysAvailable();
            boolean isPerformer = requestingEmployee.getEmployeeStatus().equals(Employee.employeeStatus.PERFORMER);
            boolean isSlacker = requestingEmployee.getEmployeeStatus().equals(Employee.employeeStatus.SLACKER);

            if (totalDaysRequested > daysAvailable) {
                if (isPerformer) {
                    allowHolidays(requestingEmployee, daysRequested, totalDaysRequested, daysAvailable);
                    return true;
                }

                return false;

            } else if (totalDaysRequested < daysAvailable) {
                if (isSlacker) {
                    return false;
                }
                allowHolidays(requestingEmployee, daysRequested, totalDaysRequested, daysAvailable);
                return true;

            }

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return false;
        }


        return false;


    }

    /**
     * Private method for holidays calculations
     *
     * @param requestingEmployee represents employee requesting for holidays
     * @param daysRequested by the employee
     * @param daysAvailable days not used yet by the employee
     * @param totalDaysRequested sum of days already used and days requested now
     */
    private void allowHolidays(Employee requestingEmployee, int daysRequested, int totalDaysRequested, int daysAvailable) {
        requestingEmployee.setHolidaysAvailable(daysAvailable - daysRequested);
        requestingEmployee.setHolidaysUsed(totalDaysRequested);
        sendNotificationEmail(requestingEmployee, daysRequested);
        inMemoryRepository.update(requestingEmployee);
    }

    /**
     * Private method for sending email notifications
     *
     * @param requestingEmployee represents employee requesting for holidays
     * @param daysRequested by the employee
     */
    private void sendNotificationEmail(Employee requestingEmployee, int daysRequested) {
        final String bossEmail = "boss@zf.com";
        final String text = "Employee: " + requestingEmployee.getName() +
                " " + requestingEmployee.getLastname() +
                " has requested: " + daysRequested + "holidays" +
                "\n His status is: " + requestingEmployee.getEmployeeStatus() +
                "\n His remaining days of Holidays is: " + requestingEmployee.getHolidaysAvailable() +
                "\n He has already used: " + requestingEmployee.getHolidaysUsed();
        final String subject = "Holiday request for:" + requestingEmployee.getName() + " " + requestingEmployee.getLastname();
        mailService.sendMail(subject, bossEmail, text, true);

    }

    /**
     * Method providing data about holidays based on Employee id
     *
     * @param id of the employee
     * @return HolidayStatus type object or null if employee does not exist
     */
    public HolidayStatus getHolidayStatusById(Long id) {
        try {
            HolidayStatus holidayStatus = new HolidayStatus();
            Employee employeeFromDB = inMemoryRepository.get(id);
            if (employeeFromDB == null) {
                throw new IllegalArgumentException("Error!Employee is null");
            }
            holidayStatus.setId(employeeFromDB.getId());
            holidayStatus.setHolidaysAvailable(employeeFromDB.getHolidaysAvailable());
            holidayStatus.setHolidaysUsed(employeeFromDB.getHolidaysUsed());
            return holidayStatus;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    public void setInMemoryRepository(InMemoryRepository inMemoryRepository) {
        this.inMemoryRepository = inMemoryRepository;
    }
}
