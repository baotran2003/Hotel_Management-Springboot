package mock.project.hotelmanagement.service.impl;

import jakarta.transaction.Transactional;
import lombok.Builder;
import mock.project.hotelmanagement.dto.GuestDto;
import mock.project.hotelmanagement.entity.Guest;
import mock.project.hotelmanagement.exception.CommonFunc;
import mock.project.hotelmanagement.exception.NotFoundException;
import mock.project.hotelmanagement.exception.PresentOrFuture;
import mock.project.hotelmanagement.mapper.service.impl.ConverterServiceImpl;
import mock.project.hotelmanagement.repository.GuestRepository;
import mock.project.hotelmanagement.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@EnableCaching

public class GuestServiceImpl implements GuestService {

    @Autowired
    private ConverterServiceImpl converterService;

    @Autowired
    private GuestRepository guestRepository;

    private CommonFunc commonFunc = new CommonFunc();


    @Override
    @Transactional
    public GuestDto createGuest(GuestDto guestDto) {
        Guest guest = converterService.convertToEntity(guestDto, Guest.class);
        isValidStayPeriod(guest.getArrivalDate(), guest.getDepartureDate());

        LocalDateTime time = guest.getCreatedDate();
        guest.setCreatedDate(commonFunc.crtDate(time));

        Guest saveGuest = guestRepository.save(guest);
        GuestDto createGuestDto = converterService.convertToDto(saveGuest, guestDto.getClass());
        return createGuestDto;
    }

    @Override
    public GuestDto updateGuest(Long guestId, GuestDto guestDto) {
        Guest guest = converterService.convertToEntity(guestDto, Guest.class);
        isValidStayPeriod(guest.getArrivalDate(), guest.getDepartureDate());

        Optional<Guest> optionalGuest = guestRepository.findById(guestId);
        if(!optionalGuest.isPresent()){
            throw new NotFoundException("Guest id: " + guestId + " : not found !");
        }
        Guest existingGuest = optionalGuest.get();

        // Chuyển đổi GuestDto thành Guest tạm thời để lấy dữ liệu cập nhật
        Guest tempGuest = converterService.convertToEntity(guestDto, Guest.class);

        existingGuest.setFirstName(tempGuest.getFirstName());
        existingGuest.setLastName(tempGuest.getLastName());
        existingGuest.setBirthDay(tempGuest.getBirthDay());
        existingGuest.setNationality(tempGuest.getNationality());
        existingGuest.setPassportNumber(tempGuest.getPassportNumber());
        existingGuest.setPassportExpiredDate(tempGuest.getPassportExpiredDate());
        existingGuest.setArrivedAt(tempGuest.getArrivedAt());
        existingGuest.setArrivalDate(tempGuest.getArrivalDate());
        existingGuest.setDepartureDate(tempGuest.getDepartureDate());
        existingGuest.setEmail(tempGuest.getEmail());
        existingGuest.setPhone(tempGuest.getPhone());
        existingGuest.setNote(tempGuest.getNote());
        existingGuest.setIdNumber(tempGuest.getIdNumber());
        existingGuest.setTitle(tempGuest.getTitle());
        existingGuest.setGuestArrivalStatus(tempGuest.getGuestArrivalStatus());


        // Kiểm tra tính hợp lệ của ngày lưu trú
        isValidStayPeriod(existingGuest.getArrivalDate(), existingGuest.getDepartureDate());

        // Lưu đối tượng đã cập nhật
        Guest savedGuest = guestRepository.save(existingGuest);

        GuestDto updatedGuestDto = converterService.convertToDto(savedGuest, GuestDto.class);
        return updatedGuestDto;
    }

//    @Override
//    public List<GuestDto> getAllGuests() {
//        List<Guest> layListEntityLen = guestRepository.findAll();
//        List<GuestDto> listDtoTraVe = layListEntityLen.stream().map(n -> converterService.convertToDto(n, GuestDto.class)).collect(Collectors.toList());
//        return listDtoTraVe;
//    }
    @Override
    public Page<GuestDto> getAllGuest(Integer number, Integer size) {
        Pageable pageable = PageRequest.of(number, size);
        return guestRepository.findAll(pageable).map(guest -> converterService.convertToDto(guest, GuestDto.class));
    }

    @Override
    public GuestDto findGuestById(Long guestId) { //
        Optional<Guest> optionalGuest = guestRepository.findById(guestId);
        if (!optionalGuest.isPresent()) {
            throw new NotFoundException("Guest id: " + guestId + " : not found !");
        }
        GuestDto guestDto = converterService.convertToDto(optionalGuest.get(), GuestDto.class);
        return guestDto;
    }

    @Override
    public Boolean deleteGuestById(Long guestId) {
        Optional<Guest> optionalGuest = guestRepository.findById(guestId);
        if (!optionalGuest.isPresent()) {
            throw new NotFoundException("Guest id: " + guestId + " : not found by deleteGuest");
        }
        guestRepository.deleteById(guestId);
        return true;
    }

    public void isValidStayPeriod(LocalDateTime arrivalDate, LocalDateTime departureDate) {
        LocalDateTime today = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth(), 0, 0);
        if (arrivalDate.isBefore(today) || departureDate.isBefore(today)) {
            throw new PresentOrFuture("arrival date and departure date can not be earlier than today");
        }
        if (departureDate.isBefore(arrivalDate)) {
            throw new PresentOrFuture("Departure date can not be earlier than arrival date");
        }
    }
}
