package mock.project.hotelmanagement.exception;

import mock.project.hotelmanagement.entity.Guest;

import java.time.LocalDateTime;


public class CommonFunc {

    public LocalDateTime crtDate(LocalDateTime time) {
        if (time==null)
            time = LocalDateTime.now();
        return time;
    }
}
