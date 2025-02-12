package mock.project.hotelmanagement.service;

import mock.project.hotelmanagement.dto.GuestDto;
import org.springframework.data.domain.Page;


public interface GuestService {
    public GuestDto createGuest(GuestDto guestDto);

    public GuestDto updateGuest(Long guestId, GuestDto guestDto);

//    public List<GuestDto> getAllGuest();
    Page<GuestDto> getAllGuest(Integer number, Integer size);
    public GuestDto findGuestById(Long guestId);

    public Boolean deleteGuestById(Long guestId);
}
