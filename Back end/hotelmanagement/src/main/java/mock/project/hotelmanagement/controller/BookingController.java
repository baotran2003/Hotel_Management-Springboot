package mock.project.hotelmanagement.controller;

import java.text.ParseException;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mock.project.hotelmanagement.dto.BookingDto;
import mock.project.hotelmanagement.dto.DataResponse;
import mock.project.hotelmanagement.service.impl.BookingServiceImpl;
import mock.project.hotelmanagement.service.impl.GuestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/booking")
@Slf4j
public class BookingController {
    @Autowired
    private BookingServiceImpl bookingService;

    @Autowired
    private GuestServiceImpl guestService;

//    @GetMapping("/find-all")
//    public DataResponse getBookings() {
//        return new DataResponse(null, "Successfully find all Booking !", bookingService.findAll(), 200);
//    }

    @GetMapping("/find-all")
    public DataResponse getBookings(@RequestParam(defaultValue = "0") int number,
                                    @RequestParam(defaultValue = "10") int size) {
        return new DataResponse(null, "Successfully find all Booking !", bookingService.findAllBooking(number, size), 200);
    }

    @PostMapping("/create")
    public DataResponse createBooking(@Valid @RequestBody BookingDto bookingDto) throws ParseException {
        return new DataResponse(null, "Successfully create Booking !", bookingService.insert(bookingDto), 200);
    }

    @PostMapping("/update")
    public DataResponse updateBooking(@Valid @RequestBody BookingDto bookingDto) throws ParseException {
        return new DataResponse(null, "Successfully update Booking !", bookingService.update(bookingDto), 200);
    }

    @GetMapping("/find-by-id/{bookingUID}")
    public DataResponse findByBookingUID(@PathVariable String bookingUID) {
        return new DataResponse(null, "Successfully find by id Booking !", bookingService.findByBookingUID(bookingUID), 200);
    }

    @PostMapping("/delete")
    public DataResponse delete(@RequestBody BookingDto bookingDto) {
        return new DataResponse(null, "Successfully delete Booking !", bookingService.delete(bookingDto), 200);
    }

    @PostMapping("/get-available-room")
    public DataResponse filterAvailableRoomsByType(@RequestBody BookingDto bookingDto) {
        return new DataResponse(null, "Successfully Filter available Room !", bookingService.filterAvailableRoomsByType(bookingDto), 200);
    }
}