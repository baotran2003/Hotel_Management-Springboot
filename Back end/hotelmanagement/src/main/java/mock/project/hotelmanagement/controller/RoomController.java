package mock.project.hotelmanagement.controller;

import mock.project.hotelmanagement.dto.DataResponse;
import mock.project.hotelmanagement.dto.RoomDto;
import mock.project.hotelmanagement.service.impl.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomServiceImpl roomServiceImpl;

//    @GetMapping("/find-all")
//    public DataResponse getAllRoom() {
//        return new DataResponse(null, "Successfully find all Room !", roomServiceImpl.findAll(), 203);
//    }
    @GetMapping("/find-all")
    public DataResponse getAllRoom(@RequestParam(defaultValue = "0") int number,
                                   @RequestParam(defaultValue = "10") int size) {
        return new DataResponse(null, "Successfully find all Room !", roomServiceImpl.findAllRoom(number, size), 203);
    }

    @GetMapping("/find-by-id/{id}")
    public DataResponse getRoomById(@PathVariable Long id) {
        return new DataResponse(null, "Successfully find by id Room !", roomServiceImpl.findById(id), 203);
    }

    @PostMapping("/create")
    public DataResponse createRoom(@RequestBody RoomDto roomDto) {
        return new DataResponse(null, "Successfully create Room !", roomServiceImpl.insert(roomDto), 203);
    }

    @PostMapping("/update")
    public DataResponse updateRoom(@RequestBody RoomDto roomDto) {
        return new DataResponse(null, "Successfully update Room !", roomServiceImpl.update(roomDto), 203);
    }

    @DeleteMapping("/delete/{roomId}")
    public DataResponse deleteRoomById(@PathVariable Long roomId) {
        return new DataResponse(null, "Successfully delete Room !", roomServiceImpl.deleteRoom(roomId), 203);
    }
}

