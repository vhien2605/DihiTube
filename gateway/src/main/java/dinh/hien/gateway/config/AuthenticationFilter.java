package dinh.hien.gateway.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dinh.hien.gateway.dto.response.ApiErrorResponse;
import dinh.hien.gateway.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter implements GlobalFilter, Ordered {
    private final ObjectMapper objectMapper;
    private final PathMatcher pathMatcher = new AntPathMatcher();
    private final AuthService authService;


    private final String[] WHITE_LIST = {
            "/identity/auth/**"
    };

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Enter authentication filter at gateway......................");

        if (isPublicEndpoint(exchange.getRequest())) {
            // continue filterChain if whiteList
            return chain.filter(exchange);
        }

        //get token from header request
        List<String> authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (CollectionUtils.isEmpty(authHeaders)) {
            return unauthenticated(exchange, "Your credentials were invalid");
        }


        // verify
        // send to auth service to introspect
        String token = authHeaders.getFirst().replace("Bearer ", "");
        log.info("Token sent : {}", token);

        return authService.introspect(token).flatMap(introspectResponse -> {
            assert introspectResponse.getBody() != null;
            if (introspectResponse.getBody().getData().getIsValid())
                return chain.filter(exchange);
            else
                return unauthenticated(exchange, introspectResponse.getBody().getData().getMessage());
        }).onErrorResume(throwable -> unauthenticated(exchange, "Your credentials were invalid"));
    }

    @Override
    public int getOrder() {
        return -1;
    }


    private boolean isPublicEndpoint(ServerHttpRequest request) {
        String path = request.getURI().getPath();
        return Arrays.stream(WHITE_LIST)
                .anyMatch(pattern -> pathMatcher.match(pattern, path));
    }


    private Mono<Void> unauthenticated(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();

        ApiErrorResponse apiResponse = ApiErrorResponse.builder()
                .error("UNAUTHENTICATED")
                .message(message)
                .path(exchange.getRequest().getURI().getPath())
                .build();

        String body = null;
        try {
            body = objectMapper.writeValueAsString(apiResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(
                Mono.just(response.bufferFactory().wrap(body.getBytes())));
    }
}
