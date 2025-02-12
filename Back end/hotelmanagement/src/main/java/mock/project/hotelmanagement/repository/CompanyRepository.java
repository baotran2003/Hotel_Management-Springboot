package mock.project.hotelmanagement.repository;

import mock.project.hotelmanagement.entity.Company;
import mock.project.hotelmanagement.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
