package mock.project.hotelmanagement.controller;

import mock.project.hotelmanagement.dto.DataResponse;
import mock.project.hotelmanagement.entity.Company;
import mock.project.hotelmanagement.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyServiceImpl CompanyServiceImpl;

//    @GetMapping("/find-all")
//    public DataResponse getAllCompany() {
//
//        return new DataResponse(null, "Successfully find all Company !", CompanyServiceImpl.findAll(), 201);
//    }
    @GetMapping("/find-all")
    public DataResponse getAllCompany(@RequestParam(defaultValue = "0") int number,
                                      @RequestParam(defaultValue = "10") int size) {

        return new DataResponse(null, "Successfully find all Company !", CompanyServiceImpl.findAllPage(number, size), 201);
    }

    @GetMapping("/find-by-id/{id}")
    public DataResponse getCompanyById(@PathVariable Long id) {
        return new DataResponse(null, "Successfully find-by-id Company !", CompanyServiceImpl.findById(id), 201);
    }

    @PostMapping("/create")
    public DataResponse createCompany(@RequestBody Company company) {
        return new DataResponse(null, "Successfully create Company !", CompanyServiceImpl.insert(company), 201);
    }

    @PostMapping("/update")
    public DataResponse updateCompany(@RequestBody Company company) {
        return new DataResponse(null, "Successfully update Company !", CompanyServiceImpl.update(company), 201);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public DataResponse deleteCompanyById(@PathVariable Long id) {
        return new DataResponse(null, "Successfully delete Company !", CompanyServiceImpl.deleteById(id), 201);
    }
}

