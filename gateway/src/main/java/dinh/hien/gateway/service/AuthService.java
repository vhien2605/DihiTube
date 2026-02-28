package dinh.hien.gateway.service;

import dinh.hien.gateway.dto.request.IntrospectRequestDTO;
import dinh.hien.gateway.dto.response.ApiSuccessResponse;
import dinh.hien.gateway.dto.response.IntrospectResponseDTO;
import dinh.hien.gateway.external.AuthClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final AuthClient authClient;

    public Mono<ResponseEntity<ApiSuccessResponse<IntrospectResponseDTO>>> introspect(String token) {
        return authClient.introspect(
                        IntrospectRequestDTO.builder()
                                .accessToken(token)
                                .build()
                )
                .doOnError(error -> {
                    log.error("CALL AUTH FAILED caused by", error);
                });
    }
}
