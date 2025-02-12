package mock.project.hotelmanagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomDto extends BaseDto{

    @NotNull
    private String roomNumber;

    @NotNull
    private String roomType;

    @NotNull
    private Long price;

    @NotNull
    private Integer maxCapacity;

    @NotNull
    private String roomStatus;
}
