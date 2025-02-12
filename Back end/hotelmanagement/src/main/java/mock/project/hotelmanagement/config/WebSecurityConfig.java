package mock.project.hotelmanagement.config;

import lombok.RequiredArgsConstructor;
import mock.project.hotelmanagement.config.JwtAuthConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Optional;

@RequiredArgsConstructor
@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {

    public static final String FOM = "FRONT_OFFICE_MANAGER";
    public static final String FO = "FRONT_OFFICE";

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable)
                .cors(Customizer.withDefaults());
        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(
                                "/v2/api-docs/**", "/v3/api-docs.yaml/**","/v3/api-docs/**",
                                "/swagger-ui.html", "/swagger-ui/**"
                        ).permitAll()
                        .requestMatchers(
                                "/auth/login", "/auth/new-access-token", "/auth/logout"
                        ).permitAll()
                        .requestMatchers(
                                "/room/create", "/room/update", "/room/delete",
                                "/booking/delete",
                                "/user/**"
                        ).hasRole(FOM)
                        .requestMatchers(
                                "/room/find**",
                                "/company/**",
                                "/guest/**",
                                "/booking/find**", "/booking/create", "/booking/update", "/booking/get-available-room"
                        ).hasAnyRole(FO, FOM)
                        .anyRequest().authenticated())
                .logout(logout -> logout.logoutUrl("/logout").invalidateHttpSession(true).clearAuthentication(true))
                .oauth2ResourceServer(auth -> auth.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)));
        return http.build();
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String userIdByToken = authentication.getName();

                return Optional.ofNullable(userIdByToken);
            }
        };
    }
}