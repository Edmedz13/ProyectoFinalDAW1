package proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import proyecto.venta_de_libros.daw1.ProyectoVentaLibrosDaw1.service.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

         http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.disable());

         //definir rutas protegidas y quien puede acceder a ellas
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/manage/login").permitAll()
//                        .requestMatchers("/manage/start").hasAnyRole("ADMIN")
//                        .anyRequest().authenticated()
//                )
//
//
//                .exceptionHandling(ex -> ex
//                        .accessDeniedHandler((req, rsp, e) -> {
//                            rsp.sendRedirect("/manage/restricted");
//                        })
//                )
//
//
//                .formLogin(form -> form
//                        .loginPage("/manage/login")
//                        .defaultSuccessUrl("/manage/start", false)
//                        .permitAll()
//                )
//
//
//                .logout(logout -> logout
//                        .logoutUrl("/manage/logout")
//                        .logoutSuccessUrl("/manage/login?logout")
//                        .permitAll()
//                );

        return http.build();
    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//        return username -> User.builder()
//                .username("Edmedz")
//                .password(passwordEncoder().encode("123456"))
//               .roles("ADMIN")
//               .build();
//    }



    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
