package mock.project.hotelmanagement.service;
import mock.project.hotelmanagement.dto.DataResponse;
import mock.project.hotelmanagement.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
//    List<Company> findAll();

    Company insert(Company Company);

    Company update(Company Company);

    Page<Company> findAllPage(Integer number, Integer size);

    Company findById(Long id);

    public Boolean deleteById(Long id);
}