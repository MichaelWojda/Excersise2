package pl.mw.zadanie2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.mw.zadanie2.model.HolidayRequest;
import pl.mw.zadanie2.model.HolidayStatus;
import pl.mw.zadanie2.services.HolidayService;

/**
 * Service class holding methods
 * for holidays related HTTP requests
 *
 * @author Michał Wojda
 * @version alpha
 */
@RestController
@RequestMapping("/holidays")
public class HolidaysController {

    /**
     * Service
     *
     * @see pl.mw.zadanie2.services.HolidayService
     */
    private HolidayService holidayService;

    /**
     * Constructor
     */
    @Autowired
    public HolidaysController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    /**
     * Accepts POST HTTP Request with HolidayRequest param
     * and processes it using HolidayService
     *
     * @param holidayRequest object containing id of the employee and number of holidays requested
     */
    @PostMapping
    public void issueHolidayRequest(@RequestBody HolidayRequest holidayRequest) {
        if (holidayService.requestForHolidays(holidayRequest)) {
            System.out.println("Holidays request processed properly");
        } else {
            System.err.println("Holidays have not been processed, please contact your superior directly ");
        }

    }
    /**
     * Accepts GET HTTP Request with id param and returns HolidayStatus object
     *
     * @see pl.mw.zadanie2.model.HolidayStatus
     * @param id path variable of the employee requesting for holidays
     */
    @GetMapping(path = "/{id}")
    public HolidayStatus getHolidayStatus(@PathVariable Long id) {
        return holidayService.getHolidayStatusById(id);
    }
}
