package mock.project.hotelmanagement.repository;

import mock.project.hotelmanagement.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query(value = "select * from room r where r.id not in (SELECT b.room_id FROM booking b where b.check_in_date = :checkInDate or (b.check_in_date > :checkInDate and b.check_in_date < :checkOutDate) or (b.check_out_date > :checkInDate and b.check_out_date < :checkOutDate) or (b.check_in_date < :checkInDate and b.check_out_date > :checkOutDate))", nativeQuery=true)
    List<Room> filterAvailableRoomsByType(
            @Param("checkInDate") LocalDateTime checkInDate,
            @Param("checkOutDate") LocalDateTime checkOutDate);
}
