package mock.project.hotelmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import mock.project.hotelmanagement.enums.BookingStatus;
import mock.project.hotelmanagement.enums.Title;
import org.hibernate.annotations.Where;
import java.time.LocalDateTime;

@Entity
@Table(name = "guest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted=false")
public class Guest extends BaseEntity {
    @Column(name = "first_name")
    @NotNull(message = "First Name is required")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only letters are allowed.")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last Name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Only letters are allowed.")
    private String lastName;

    @Column(name = "birthday")
    @NotNull(message = "Birth Day is required")
    private LocalDateTime birthDay;

    @Column(name = "nationality")
    @NotNull(message = "Nationality is required")
    private String nationality;

    @Column(name = "passport_number")
    @NotNull(message = "Passport Number is required")
    private String passportNumber;

    @Column(name = "passport_expired_date")
    private LocalDateTime passportExpiredDate;

    @Column(name = "arrived_at")
    private String arrivedAt;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "note")
    private String note;

    @Column(name = "id_number")
    @NotNull(message = "Id Number is required")
    private String idNumber;

    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    @Column(name = "title")
    @Enumerated(EnumType.STRING)
    private Title title;

//    @Column
//    @FutureOrPresent(message = "Check-in date must be in the future or present")
//    @NotNull(message = "Check-in date is required")
//    private LocalDateTime checkInDate;
//
//    @Column
//    @NotNull(message = "Check-out date is required")
//    private LocalDateTime checkOutDate;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private BookingStatus guestArrivalStatus;
}
