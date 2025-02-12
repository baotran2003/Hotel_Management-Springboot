package mock.project.hotelmanagement.repository;

import mock.project.hotelmanagement.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {

}
