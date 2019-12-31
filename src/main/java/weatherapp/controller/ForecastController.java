package weatherapp.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import weatherapp.ForecastService;
import weatherapp.model.currentforecastmodel.CurrentForecast;
import weatherapp.model.futureforecastmodel.FutureForecast;

import java.io.IOException;

@RestController
public class ForecastController {

    private final ForecastService forecastService;

    @Autowired
    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/forecast")
    public CurrentForecast getCurrentWeather(@RequestParam String city, @RequestParam(required = false) String language,
                                             @RequestParam(required = false) String units)
            throws UnirestException, IOException {

        return forecastService.getCurrentForecast(city, language, units);
    }
}
