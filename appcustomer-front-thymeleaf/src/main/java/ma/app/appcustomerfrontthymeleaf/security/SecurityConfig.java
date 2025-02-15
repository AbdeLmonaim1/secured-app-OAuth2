package ma.app.appcustomerfrontthymeleaf.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private ClientRegistrationRepository clientRegistrationRepository;
    public SecurityConfig(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(Customizer.withDefaults())
                //Cette ressources ne necessaiste pas une authentification
                .authorizeHttpRequests(ar->ar.requestMatchers("/","/webjars/**").permitAll())
                //Toutes les requetes necessaiste une authentification
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .logout(logout-> logout
                        .logoutSuccessUrl("/").permitAll()
                        .deleteCookies("JSESSIONID"))
                .build();
    }

}
