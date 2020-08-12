package pl.sda.ldz24.finalapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource; //jest jedna DB skonfigurowana -> więc ona się wstrzyknie

    @Override
    protected void configure(HttpSecurity http) throws Exception {  //konfiguracja zabezpieczeń
        http.authorizeRequests()
                .antMatchers("/auth/*").permitAll()  //antMatcher filtruje zakresy adresów URL, permitAll powoduje otwarcie ich na wszystkich (nawet niezalogowanych) użytkowników -> w przypadku jednej * obejmuje to tylko jeden poziom
                .antMatchers("/css/**").permitAll()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/weather/**").permitAll() //dwie gwaizdki powodują matchowanie jakiegokolwiek adresu aczynającego się od podanego  -> /weather/my/current
                .anyRequest().authenticated() //anyRequest jest wskazaniem na wszystkie pozostałe zakresy URLi, authenticated mówi o tym, że użytkownik musi być zalogowany
                .and()
                .csrf().disable()
                .headers().frameOptions().disable().and()
                .formLogin()
                .loginPage("/auth/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/loginProcess") // to jest url na który zostaną wysłane dane do logowania
                .failureUrl("/auth/login?error=1")
                .defaultSuccessUrl("/") //url do przekierowania po zalogowaniu
                .and()
                .logout()
                .logoutUrl("/logout")  //jesli zostanie wykonany taki request to uzytkownik zostanie wylogowany
                .logoutSuccessUrl("/auth/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //konfiguracja odpytywania o haslo i role uzytkownika
        //tu definijemy źródła danych o uzytkownikach

        auth.inMemoryAuthentication() //uzytkownik funkcjonujący w pamięci aplikacji
                .withUser("user@user.com")
                .password(passwordEncoder.encode("123123"))
                .roles("USER");

        auth.jdbcAuthentication() // odczyt użytkowników z bazy danych
                .usersByUsernameQuery("select u.LOGIN, u.PASSWORD, 1 from USER u where u.LOGIN=?") //zapytanie wyciagajace dane potrzebne do autentykacji
                .authoritiesByUsernameQuery("SELECT u.LOGIN, r.role_name, 1\n" + //zapytanie wyciagajace dane o rolach (autoryzacja), 1 to status użytkownika - aktywny
                        "from USER u\n" +
                        "join USER_ROLES UR on u.ID = UR.USER_ID\n" +
                        "join ROLE R on UR.ROLES_ID = R.ID\n" +
                        "where u.LOGIN=?")
                .dataSource(dataSource)  //podajemy, gdzie jest źródło danych (baza danych)
                .passwordEncoder(passwordEncoder); //sposob hashowania haseł

    }
}
