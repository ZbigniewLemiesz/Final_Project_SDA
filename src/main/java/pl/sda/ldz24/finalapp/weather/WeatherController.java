package pl.sda.ldz24.finalapp.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.ldz24.finalapp.weather.model.WeatherWrapper;

@RestController // wszystkie metody będą zwracać JSONy
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping("/weather")
    public ResponseEntity<WeatherWrapper> weather() {
        return ResponseEntity.ok(weatherService.downloadWeather());
    }
}
