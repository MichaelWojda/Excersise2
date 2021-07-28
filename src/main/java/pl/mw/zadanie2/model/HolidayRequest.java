package pl.mw.zadanie2.model;

/**
 * HolidayRequest class holds id of the employee as well as
 * number of off days that he/she requests
 *
 * @author Michał Wojda
 * @version alpha
 */
public class HolidayRequest {

    /**
     * Id of the employee that is issuing request
     */
    private Long employeeId;
    /**
     * Number of off days requested by employee
     */
    private int daysRequested;

    /**
     * Two parameter constructor
     */
    public HolidayRequest(Long employeeId, int daysRequested) {
        this.employeeId = employeeId;
        this.daysRequested = daysRequested;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public int getDaysRequested() {
        return daysRequested;
    }

    public void setDaysRequested(int daysRequested) {
        this.daysRequested = daysRequested;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HolidayRequest)) return false;

        HolidayRequest that = (HolidayRequest) o;

        if (getDaysRequested() != that.getDaysRequested()) return false;
        return getEmployeeId() != null ? getEmployeeId().equals(that.getEmployeeId()) : that.getEmployeeId() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmployeeId() != null ? getEmployeeId().hashCode() : 0;
        result = 31 * result + getDaysRequested();
        return result;
    }
}
