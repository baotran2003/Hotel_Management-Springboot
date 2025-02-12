package mock.project.hotelmanagement.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import mock.project.hotelmanagement.dto.BookingDto;
import mock.project.hotelmanagement.dto.DataResponse;
import mock.project.hotelmanagement.entity.Room;
import mock.project.hotelmanagement.enums.RoomType;
import mock.project.hotelmanagement.exception.NotFoundException;
import mock.project.hotelmanagement.exception.PresentOrFuture;
import mock.project.hotelmanagement.repository.BookingRepository;
import mock.project.hotelmanagement.repository.CompanyRepository;
import mock.project.hotelmanagement.repository.GuestRepository;
import mock.project.hotelmanagement.repository.RoomRepository;
import mock.project.hotelmanagement.service.BookingService;
import mock.project.hotelmanagement.entity.Booking;
import mock.project.hotelmanagement.mapper.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    ConverterService converterService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private CompanyRepository companyRepository;

//    @Override
//    public List<BookingDto> findAll() {
//        List<BookingDto> result = bookingRepository.findAll().stream().map(n -> converterService.convertToDto(n, BookingDto.class)).collect(
//                Collectors.toList());
//        return result;
//    }

    @Override
    public Boolean delete(BookingDto bookingDto) {
        Booking bookingEntity = converterService.convertToEntity(bookingDto, Booking.class);
        bookingEntity.getGuests().forEach(guest -> {
            guest.setDeleted(true);
        });
        bookingEntity.setDeleted(true);
        bookingRepository.save(bookingEntity);
        return true;
    }

    @Override
    public BookingDto update(BookingDto bookingDto) {
        Booking bookingEntity = converterService.convertToEntity(bookingDto, Booking.class);
        isValidStayPeriod(bookingEntity.getCheckInDate(), bookingEntity.getCheckOutDate());
        Booking insertedEntity = bookingRepository.save(bookingEntity);
        BookingDto result = converterService.convertToDto(insertedEntity, BookingDto.class);
        return result;
    }

    @Override
    public BookingDto insert(BookingDto bookingDto) {
        Booking bookingEntity = converterService.convertToEntity(bookingDto, Booking.class);
        isValidStayPeriod(bookingEntity.getCheckInDate(), bookingEntity.getCheckOutDate());
        if (roomRepository.findById(bookingEntity.getActualRoom().getId()).isPresent()) {
            Room handledRoom = roomRepository.findById(bookingEntity.getActualRoom().getId()).get();
            bookingEntity.setActualRoom(handledRoom);
        }
//        if (companyRepository.findById(bookingEntity.getCompany().getId()).isPresent()) {
//            Company handledCompany = companyRepository.findById(bookingEntity.getCompany().getId()).get();
//            bookingEntity.setCompany(handledCompany);
//        }
        Booking insertedEntity = bookingRepository.save(bookingEntity);
        BookingDto result = converterService.convertToDto(insertedEntity, BookingDto.class);
        return result;
    }

    @Override
    public Page<BookingDto> findAllBooking(Integer number, Integer size) {
        Pageable pageable = PageRequest.of(number, size);
        return bookingRepository.findAll(pageable).map(booking -> converterService.convertToDto(booking, BookingDto.class));
    }

    @Override
    public Optional<BookingDto> findById(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            return bookingRepository.findById(id).map(booking -> converterService.convertToDto(booking, BookingDto.class));
        } else {
            throw new NotFoundException("Not found booking id: " + id);
        }
    }

    @Override
    public Optional<BookingDto> findByBookingUID(String bookingUID) {
        Optional<Booking> optionalBookingDto = bookingRepository.findByBookingUID(bookingUID);
        if (optionalBookingDto.isPresent()) {
            return bookingRepository.findByBookingUID(bookingUID).map(booking -> converterService.convertToDto(booking, BookingDto.class));
        } else {
            throw new NotFoundException("Not found booking UID !: " + bookingUID);
        }
    }

    @Override
    public BookingDto filterAvailableRoomsByType(BookingDto bookingDto) {
        Booking bookingEntity = converterService.convertToEntity(bookingDto, Booking.class);
        List<Room> listOfAvailableRoom = roomRepository.filterAvailableRoomsByType(bookingEntity.getCheckInDate(), bookingEntity.getCheckOutDate());
        bookingDto = filterRoomByType(bookingDto, listOfAvailableRoom);
        return bookingDto;
    }

    public void isValidStayPeriod(LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        LocalDateTime today = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 0, 0);
        if (checkInDate.isBefore(today) || checkOutDate.isBefore(today)) {
            throw new PresentOrFuture("Check-in and Check-out date can not be earlier than today");
        }
        if (checkOutDate.isBefore(checkInDate) || checkOutDate.isAfter(checkInDate.plusDays(30))) {
            throw new PresentOrFuture("Check-out date must be after or no longer than 30 days to the check-in date");
        }
    }

    public BookingDto filterRoomByType(BookingDto bookingDto, List<Room> roomList) {
        List<Room> listSupRoom = new ArrayList<>();
        List<Room> listDlxRoom = new ArrayList<>();
        List<Room> listSuiteRoom = new ArrayList<>();
        List<Room> listSigSuiteRoom = new ArrayList<>();
        roomList.forEach(room -> {
            if (room.getRoomType() == RoomType.SUP) {
                listSupRoom.add(room);
            }
            if (room.getRoomType() == RoomType.DLX) {
                listDlxRoom.add(room);
            }
            if (room.getRoomType() == RoomType.SUITE) {
                listSuiteRoom.add(room);
            }
            if (room.getRoomType() == RoomType.SIG_SUITE) {
                listSigSuiteRoom.add(room);
            }
        });
        bookingDto.setListSupRooms(listSupRoom);
        bookingDto.setListDlxRooms(listDlxRoom);
        bookingDto.setListSuiteRooms(listSuiteRoom);
        bookingDto.setListSigSuiteRooms(listSigSuiteRoom);
        return bookingDto;
    }
}

