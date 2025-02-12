package mock.project.hotelmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import mock.project.hotelmanagement.enums.BookingStatus;
import mock.project.hotelmanagement.enums.RoomType;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@Where(clause = " deleted=false")
public class Booking extends BaseEntity {
    @Column
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Column
    private String bookingUID;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Guest> guests;

    @Column
    @Enumerated(EnumType.STRING)
    private RoomType bookedType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Room actualRoom;

    @Column(name = "deleted")
    private Boolean deleted = false;

    @Column
//    @FutureOrPresent(message = "Check-in date must be in the future or present")
    @NotNull(message = "Check-in date is required")
    private LocalDateTime checkInDate;

    @Column
    @NotNull(message = "Check-out date is required")
    private LocalDateTime checkOutDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Company company;

    @Column
    private boolean hasBreakfast = false;

    @Column
    private short numberOfChild;

    @Column
    private Double exchangeRate;

    @Column
    private String note;
}