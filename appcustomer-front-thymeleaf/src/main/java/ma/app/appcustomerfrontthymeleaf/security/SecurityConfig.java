package ma.app.appcustomerfrontthymeleaf.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(Customizer.withDefaults())
                //Cette ressources ne necessaiste pas une authentification
                .authorizeHttpRequests(ar->ar.requestMatchers("/","/webjars/**").permitAll())
                //Toutes les requetes necessaiste une authentification
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .logout(lougout-> lougout.logoutSuccessUrl("/").permitAll().deleteCookies("JSESSIONID"))
                .build();
    }
}
