package mock.project.hotelmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mock.project.hotelmanagement.dto.UserShowDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    private Long refreshExpiresIn;
    private UserShowDto userShowDto;
}