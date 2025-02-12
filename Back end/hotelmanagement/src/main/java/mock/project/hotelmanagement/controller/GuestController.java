package mock.project.hotelmanagement.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mock.project.hotelmanagement.dto.DataResponse;
import mock.project.hotelmanagement.dto.GuestDto;
import mock.project.hotelmanagement.service.impl.GuestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/guest")
@Slf4j
public class GuestController {
    @Autowired
    private GuestServiceImpl guestServiceImpl;

    @GetMapping("/find-all")
    public DataResponse getAllGuest(@RequestParam(defaultValue = "0") int number,
                                    @RequestParam(defaultValue = "10") int size) {
        return new DataResponse(null, "Successfully find all Guest !", guestServiceImpl.getAllGuest(number, size), 202);
    }

//    @Cacheable(value = "tutorial", key = "#guestId")
    @GetMapping("/find-by-id/{guestId}")
    public DataResponse getGuestById(@PathVariable Long guestId) {
         return new DataResponse(null, "Successfully find-by-id Guest !", guestServiceImpl.findGuestById(guestId), 202);
    }

    @PostMapping("/create")
//    @CachePut(value = "tutorial")
    public DataResponse createGuest(@Valid @RequestBody GuestDto guestDto) throws ParseException {
        return new DataResponse(null, "Successfully create Guest !", guestServiceImpl.createGuest(guestDto), 202);
    }

//    @CachePut(value = "tutorial", key = "#guestDto.id")
    @PostMapping("/update/{guestId}")
    public DataResponse updateGuest(@PathVariable Long guestId, @Valid @RequestBody GuestDto guestDto) throws ParseException {
        return new DataResponse(null, "Successfully update Guest !", guestServiceImpl.updateGuest(guestId, guestDto), 202);
    }

//    @CacheEvict(value = "tutorial", key = "#guestDto.id")
    @PostMapping("/delete/{guestId}")
    public DataResponse deleteGuestById(@PathVariable Long guestId) {
        return new DataResponse(null, "Successfully delete Guest !", guestServiceImpl.deleteGuestById(guestId), 202);
    }
}