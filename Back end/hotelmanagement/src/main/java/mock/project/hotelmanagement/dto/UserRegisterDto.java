package mock.project.hotelmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mock.project.hotelmanagement.validation.Phone;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    @NotNull
    private String userName;

    private String firstName;

    private String lastName;

    @Email
    @NotNull
    private String email;

    @Phone
    @NotNull
    private String phoneNumber;

    @NotNull
    private String password;
}
