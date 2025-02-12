package mock.project.hotelmanagement.service;

import mock.project.hotelmanagement.dto.BookingDto;
import mock.project.hotelmanagement.dto.DataResponse;
import mock.project.hotelmanagement.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookingService {
//    List<BookingDto> findAll();

    Boolean delete(BookingDto bookingDto);

    BookingDto insert(BookingDto bookingDto);

    BookingDto update(BookingDto bookingDto);

    Page<BookingDto> findAllBooking(Integer number, Integer size);

    Optional<BookingDto> findById(Long id);

    Optional<BookingDto> findByBookingUID(String bookingUID);

    BookingDto filterAvailableRoomsByType(BookingDto bookingDto);
}