package mock.project.hotelmanagement.repository;

import mock.project.hotelmanagement.entity.Booking;
import mock.project.hotelmanagement.entity.Room;
import mock.project.hotelmanagement.enums.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByBookingUID(String bookingUID);
    Page<Booking> findAll(Pageable pageable);
}
