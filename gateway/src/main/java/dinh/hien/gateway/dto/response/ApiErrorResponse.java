package dinh.hien.gateway.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse extends ApiResponse implements Serializable {
    private String error;
    private String path;
}