package mock.project.hotelmanagement.service;

import mock.project.hotelmanagement.dto.RoomDto;
import org.springframework.data.domain.Page;

public interface RoomService {

    RoomDto insert(RoomDto roomDto);

    RoomDto update(RoomDto roomDto);

    Page<RoomDto> findAllRoom(Integer number, Integer size);

    RoomDto findById(Long roomId);

    Boolean deleteRoom(Long guestId);
}
