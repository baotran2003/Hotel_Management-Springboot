package mock.project.hotelmanagement.service.impl;

import mock.project.hotelmanagement.dto.BookingDto;
import mock.project.hotelmanagement.dto.DataResponse;
import mock.project.hotelmanagement.entity.Company;
import mock.project.hotelmanagement.entity.Company;
import mock.project.hotelmanagement.exception.NotFoundException;
import mock.project.hotelmanagement.mapper.service.impl.ConverterServiceImpl;
import mock.project.hotelmanagement.repository.CompanyRepository;
import mock.project.hotelmanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ConverterServiceImpl converterServiceImpl;

    @Override
    public Company insert(Company company) {
        Company insertedCompany = companyRepository.save(company);
        return converterServiceImpl.convertToDto(insertedCompany, Company.class);
    }

    @Override
    public Company update(Company company) {
        Company updatedCompany = companyRepository.save(company);
        return converterServiceImpl.convertToDto(updatedCompany, Company.class);
    }

    @Override
    public Page<Company> findAllPage(Integer number, Integer size) {
        Pageable pageable = PageRequest.of(number, size);
        return companyRepository.findAll(pageable);
    }

//    @Override
//    public List<Company> findAll() {
//        List<Company> listCompany = companyRepository.findAll();
//        return listCompany;
//    }

    @Override
    public Company findById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (!company.isPresent()) {
            throw new NotFoundException("Not found Company with Id: " + id);
        }
        return company.get();
    }

    @Override
    public Boolean deleteById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (!company.isPresent()) {
            throw new NotFoundException("Not found Company with Id: " + id);
        }
        companyRepository.deleteById(id);
        return true;
    }
}
