package mock.project.hotelmanagement.service.impl;

import mock.project.hotelmanagement.dto.RoomDto;
import mock.project.hotelmanagement.exception.NotFoundException;
import mock.project.hotelmanagement.mapper.service.impl.ConverterServiceImpl;
import mock.project.hotelmanagement.repository.RoomRepository;
import mock.project.hotelmanagement.service.RoomService;
import mock.project.hotelmanagement.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ConverterServiceImpl converterServiceImpl;

    @Override
    public RoomDto insert(RoomDto roomDto) {
        Room newRoom = converterServiceImpl.convertToEntity(roomDto, Room.class);
        Room insertedRoom = roomRepository.save(newRoom);
        return converterServiceImpl.convertToDto(insertedRoom, RoomDto.class);
    }

    @Override
    public RoomDto update(RoomDto roomDto) {
        Room newRoom = converterServiceImpl.convertToEntity(roomDto, Room.class);
        Room updatedRoom = roomRepository.save(newRoom);
        return converterServiceImpl.convertToDto(updatedRoom, RoomDto.class);
    }

    @Override
    public Page<RoomDto> findAllRoom(Integer number, Integer size) {
        Pageable pageable = PageRequest.of(number, size);
        return roomRepository.findAll(pageable).map(room -> converterServiceImpl.convertToDto(room, RoomDto.class));
    }

    @Override
    public RoomDto findById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (!room.isPresent()) {
            throw new NotFoundException("Not found Room with Id: " + id);
        }
        RoomDto roomDto = converterServiceImpl.convertToDto(room.get(), RoomDto.class);
        return roomDto;
    }

    @Override
    public Boolean deleteRoom(Long roomId) {

        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (!optionalRoom.isPresent()) {
            throw new NotFoundException("Not found Room with Id: " + roomId);
        }
        roomRepository.deleteById(roomId);
        return true;
    }
}
