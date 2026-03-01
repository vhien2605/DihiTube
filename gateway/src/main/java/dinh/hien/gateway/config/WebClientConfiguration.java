package dinh.hien.gateway.config;


import dinh.hien.gateway.external.AuthClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfiguration {
    // add domain
    @Bean
    public AuthClient authClient(ReactorLoadBalancerExchangeFilterFunction lbFunction) {
        WebClient webClient = WebClient.builder()
                .baseUrl("lb://identity-service")
                // add load balancer manually
                .filter(lbFunction)
                .defaultHeader("Content-Type", "application/json")
                .build();
        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory
                        .builderFor(WebClientAdapter.create(webClient))
                        .build();
        return factory.createClient(AuthClient.class);
    }


    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsWebFilter(source);
    }
}
