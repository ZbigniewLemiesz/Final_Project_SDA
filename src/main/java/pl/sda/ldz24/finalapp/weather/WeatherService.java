package pl.sda.ldz24.finalapp.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sda.ldz24.finalapp.users.UserContextHolder;
import pl.sda.ldz24.finalapp.users.UserService;
import pl.sda.ldz24.finalapp.users.WeatherAddressDTO;
import pl.sda.ldz24.finalapp.weather.model.WeatherWrapper;

@Service
public class WeatherService {

    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final String API_KEY = "ea900b66f547fd7b23625544873a4200";

    @Autowired
    private UserContextHolder userContextHolder;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate weatherRestTemplate;

    WeatherWrapper downloadWeather() {
        String userEmail = userContextHolder.getUserEmail();

        WeatherAddressDTO weatherAddressDTO = userService.findWeatherAddressByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono adresu uzytkownika"));

        String url = API_URL + "?q=" + weatherAddressDTO.getCity() + "," + weatherAddressDTO.getCountry().getSymbol()
                + "&appid=" + API_KEY
                + "&units=metric"
                + "&lang=pl";
        ResponseEntity<WeatherWrapper> responseEntity = weatherRestTemplate.getForEntity(url, WeatherWrapper.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            return null;
        }

    }
}
