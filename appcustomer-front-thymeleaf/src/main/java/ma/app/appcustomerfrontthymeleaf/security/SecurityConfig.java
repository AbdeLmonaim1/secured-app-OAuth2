package ma.app.appcustomerfrontthymeleaf.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
//Pour activer la security avec les roles
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    //Les trois client(registration) Oauth2 (Gmail, Github, Keycloack) sont stockes par Spring Security dans un objet de type ClientRegistrationRepository
//    private ClientRegistrationRepository clientRegistrationRepository;
//    public SecurityConfig(ClientRegistrationRepository clientRegistrationRepository) {
//        this.clientRegistrationRepository = clientRegistrationRepository;
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                //Puique on a utiliser une une authentification statFull(HTML rendu cote server) on a besoin d'activer crsf()
                .csrf(Customizer.withDefaults())
                //Cette ressources ne necessaiste pas une authentification
                .authorizeHttpRequests(ar->ar.requestMatchers("/","/oauth2Login/**","/webjars/**").permitAll())
                //Toutes les requetes necessaiste une authentification
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                .oauth2Login(ol->ol.loginPage("/oauth2Login")
                                .defaultSuccessUrl("/")
                        )
                .logout(logout-> logout
//                        .logoutSuccessHandler(oidcLogoutSuccessHandler())
                        .logoutSuccessUrl("/").permitAll()
//                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID"))
                .exceptionHandling(eh->eh.accessDeniedPage("/notAuthorized"))
                .build();
    }
//    private OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() {
//        final OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
//                new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);
//        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/logoutsuccess=true");
//        return oidcLogoutSuccessHandler;
//    }
    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        //les authorites existe dans le context de Security car on a deux types de utilisateurs (OAUTH2_USER et OIDC_USER)
        return authorities -> {
            //Collection des roles
            final Set<GrantedAuthority> mapperAuthorities = new HashSet<>();
            authorities.forEach((authority) ->{
                if (authority instanceof OidcUserAuthority oidcUserAuthority) {
                    mapperAuthorities.addAll(mapAuthorities(oidcUserAuthority.getIdToken().getClaims()));
                    System.out.println(oidcUserAuthority);
                } else if (authority instanceof OAuth2UserAuthority oauth2UserAuthority) {
                    mapperAuthorities.addAll(mapAuthorities(oauth2UserAuthority.getAttributes()));
                }
            });
                return mapperAuthorities;
        };
    }
    public List<SimpleGrantedAuthority> mapAuthorities(final Map<String, Object> attributes) {
        final Map<String, Object> realmsAccess = (Map<String, Object>) attributes.getOrDefault("realm_access", Collections.emptyMap());
        final Collection<String> roles = (Collection<String>) realmsAccess.getOrDefault("roles", Collections.emptyList());
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
