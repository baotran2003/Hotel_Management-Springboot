package mock.project.hotelmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import mock.project.hotelmanagement.enums.BookingStatus;
import mock.project.hotelmanagement.enums.Title;
import mock.project.hotelmanagement.validation.Phone;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class GuestDto extends BaseDto {
    @JsonProperty
    @NotNull(message = "First Name is required")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Only letters are allowed.")
    private String firstName;

    @JsonProperty
    @NotNull(message = "Last Name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Only letters are allowed.")
    private String lastName;

    @JsonProperty
    @NotNull(message = "Birth Day is required")
    private String birthDay;

    @JsonProperty
    @NotNull(message = "Nationality is required")
    private String nationality;

    @JsonProperty
    @NotNull(message = "Passport Number is required")
//    @Pattern(regexp = "^[A-zĐ]{1}[0-9]{7}$", message = "The passport must have 1 uppercase letter and 7 digits.")
    private String passportNumber;

    @JsonProperty
    private String passportExpiredDate;

    @JsonProperty
    private String arrivedAt;

    @JsonProperty
    private String arrivalDate;

    @JsonProperty
    // Khong duoc truoc ngay arrivalDate va khong duoc truoc ngay hien tai
    private String departureDate;

    @JsonProperty
    @Email
    private String email;

    @JsonProperty
    @Phone(message = "Phone number is not valid !!!!!")
    private String phone;

    @JsonProperty
    private String note;

    @JsonProperty
    @NotNull(message = "Id Number is required")
    private String idNumber;

    @Column(name = "title")
    private String title;

    @Column
    @NotNull
    private String guestArrivalStatus;
}