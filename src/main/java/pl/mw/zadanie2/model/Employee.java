package pl.mw.zadanie2.model;

/**
 * Class representing employee of the company
 *
 * @author Michał Wojda
 * @version alpha
 */

public class Employee {


    /**
     * Identifier
     */
    private Long id;
    /**
     * Name of the employee
     */
    private String name;
    /**
     * Lastname of the employee
     */
    private String lastname;
    /**
     * Password of the employee
     */
    private String password;

    /**
     * Internal enum with possible employee status
     */
    public enum employeeStatus {PERFORMER, SLACKER, STANDARD}

    /**
     * Status of the employee
     */
    private employeeStatus employeeStatus;
    /**
     * Number of Holidays used by employee
     */
    private int holidaysUsed;
    /**
     * Number of employee's remaining holidays
     */
    private int holidaysAvailable;


    /**
     * Empty constructor
     */
    public Employee() {
    }

    /**
     * Constructor with parameters
     */
    public Employee(String name, String lastname, String password, Employee.employeeStatus employeeStatus, int holidaysUsed, int holidaysAvailable) {
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.employeeStatus = employeeStatus;
        this.holidaysUsed = holidaysUsed;
        this.holidaysAvailable = holidaysAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee.employeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(Employee.employeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public int getHolidaysAvailable() {
        return holidaysAvailable;
    }

    public void setHolidaysAvailable(int holidaysAvailable) {
        this.holidaysAvailable = holidaysAvailable;
    }

    public int getHolidaysUsed() {
        return holidaysUsed;
    }

    public void setHolidaysUsed(int holidaysUsed) {
        this.holidaysUsed = holidaysUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (getHolidaysUsed() != employee.getHolidaysUsed()) return false;
        if (getHolidaysAvailable() != employee.getHolidaysAvailable()) return false;
        if (getName() != null ? !getName().equals(employee.getName()) : employee.getName() != null) return false;
        if (getLastname() != null ? !getLastname().equals(employee.getLastname()) : employee.getLastname() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(employee.getPassword()) : employee.getPassword() != null)
            return false;
        return getEmployeeStatus() == employee.getEmployeeStatus();
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getLastname() != null ? getLastname().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getEmployeeStatus() != null ? getEmployeeStatus().hashCode() : 0);
        result = 31 * result + getHolidaysUsed();
        result = 31 * result + getHolidaysAvailable();
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", employeeStatus=" + employeeStatus +
                ", holidaysUsed=" + holidaysUsed +
                ", holidaysAvailable=" + holidaysAvailable +
                '}';
    }
}
