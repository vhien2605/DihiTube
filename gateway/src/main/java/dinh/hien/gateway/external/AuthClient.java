package dinh.hien.gateway.external;

import dinh.hien.gateway.dto.request.IntrospectRequestDTO;
import dinh.hien.gateway.dto.response.ApiSuccessResponse;
import dinh.hien.gateway.dto.response.IntrospectResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface AuthClient {
    @PostExchange(url = "/identity/auth/introspect", contentType = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ApiSuccessResponse<IntrospectResponseDTO>>> introspect(@RequestBody IntrospectRequestDTO dto);
}