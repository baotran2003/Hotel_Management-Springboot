package mock.project.hotelmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mock.project.hotelmanagement.enums.RoomStatus;
import mock.project.hotelmanagement.enums.RoomType;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "room")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE room SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Room extends BaseEntity {

    @Column(name = "room_number")
    @NotNull
    private String roomNumber;

    @Column(name = "room_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoomType roomType;

    @Column(name = "price", precision = 10, scale = 2)
    @NotNull
    private BigDecimal price;

    @Column(name = "max_capacity")
    @NotNull
    private Integer maxCapacity;

    @Column(name = "room_status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

    @Column
    private String note;

    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;
}
