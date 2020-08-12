package pl.sda.ldz24.finalapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration//specjalna klasa z ustawieniami aplikacji
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() { //to musimy zadeklarować - konfiguracja sposobu hashowania haseł
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate weatherRestTemplate(){
        return new RestTemplate();
    }
}
