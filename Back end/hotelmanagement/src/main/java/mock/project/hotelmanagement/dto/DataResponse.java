package mock.project.hotelmanagement.dto;

import lombok.*;

@Data
 @AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DataResponse {
    private String error;

    private String message;

    private Object data;

    private int status;
}
