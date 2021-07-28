package pl.mw.zadanie2.model;

/**
 * HolidayStatus class holds information about
 * id of the employee as well as
 * number of his holidays available and already used
 *
 * @author Michał Wojda
 * @version alpha
 */

public class HolidayStatus {

    /**
     * Id of the employee
     */
    private Long id;
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
    public HolidayStatus() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHolidaysUsed() {
        return holidaysUsed;
    }

    public void setHolidaysUsed(int holidaysUsed) {
        this.holidaysUsed = holidaysUsed;
    }

    public int getHolidaysAvailable() {
        return holidaysAvailable;
    }

    public void setHolidaysAvailable(int holidaysAvailable) {
        this.holidaysAvailable = holidaysAvailable;
    }

    @Override
    public String toString() {
        return "HolidayStatus{" +
                "id=" + id +
                ", holidaysUsed=" + holidaysUsed +
                ", holidaysAvailable=" + holidaysAvailable +
                '}';
    }
}
