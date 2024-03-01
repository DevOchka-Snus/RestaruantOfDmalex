package com.dmalex.ordermanagementsystem.config;

import com.dmalex.ordermanagementsystem.security.PersonTokenFilter;
import com.dmalex.ordermanagementsystem.security.PersonTokenProvider;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AppConfig {
    private final PersonTokenProvider personTokenProvider;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("auth"))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "auth",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(
                        new Info()
                                .title("Ресторан Dmalex")
                                .description("Приложение для обработки заказов в ресторане")
                                .version("1.0")
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS
                                )
                )
                .exceptionHandling(configurer ->
                        configurer
                                .authenticationEntryPoint(
                                        (request, response, authException) ->
                                        {
                                            response.setStatus(
                                                            HttpStatus.UNAUTHORIZED.value()
                                                    );
                                            response.getWriter().write("Unauthorized");
                                        })
                                .accessDeniedHandler(
                                        ((request, response, accessDeniedException) -> {
                                            response.setStatus(
                                                    HttpStatus.UNAUTHORIZED.value()
                                            );
                                            response.getWriter().write("Access denied");
                                        }
                                )
                                ))
                .authorizeHttpRequests(configurer ->
                        configurer.requestMatchers("/api/v1/auth/**")
                                .permitAll()
                                .requestMatchers("/swagger-ui/**")
                                .permitAll()
                                .requestMatchers("/v3/api-docs/**")
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .anonymous(AbstractHttpConfigurer::disable)
                .addFilterBefore(new PersonTokenFilter(personTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean(name = "onePriorityExecutor")
    public AsyncTaskExecutor onePriorityExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("OnePriority-");
        executor.setThreadPriority(1);
        executor.initialize();
        return executor;
    }
}
