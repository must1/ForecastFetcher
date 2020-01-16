package weatherapp;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;
import weatherapp.model.currentforecastmodel.CurrentForecast;
import weatherapp.model.futureforecastmodel.FutureForecast;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ForecastService {

        private static final String X_RAPID_HOST = "community-open-weather-map.p.rapidapi.com";
        private static final String X_RAPID_API_KEY = "...";
        private static final String CURRENT_FORECAST_HOST = "https://community-open-weather-map.p.rapidapi.com/weather";
        private static final String FORECAST_FOR_DAYS_AHEAD_HOST = "https://community-open-weather-map.p.rapidapi.com/forecast/daily";
        private static final String NUMBER_OF_DAYS_AHEAD_QUERY = "cnt";
        private static final String CITY_QUERY = "q";
        private static final String UNITS_QUERY = "units";
        private static final String LANGUAGE_QUERY = "lang";
        private Map<String, Object> queryMap = new LinkedHashMap<>();

        public CurrentForecast getCurrentForecast(String city, String language, String units) {
                HttpResponse<JsonNode> response = getJsonResponseFromUrl(city, language, units, null,
                        CURRENT_FORECAST_HOST);

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                try {
                        return objectMapper.readValue(response.getBody().toString(), CurrentForecast.class);
                } catch (IOException e) {
                        throw new IllegalArgumentException("Cannot map " + response.getBody().toString() + " to CurrentForecast class due to " + e.getMessage());
                }
        }

        public FutureForecast getForecastForDaysAhead(String city, String language, String units, Integer numberOfDaysAhead) {
                HttpResponse<JsonNode> response = getJsonResponseFromUrl(city, language, units, numberOfDaysAhead,
                        FORECAST_FOR_DAYS_AHEAD_HOST);

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

                try {
                        return objectMapper.readValue(response.getBody().toString(), FutureForecast.class);
                } catch (IOException e) {
                        throw new IllegalArgumentException("Cannot map " + response.getBody().toString() + " to FutureForecast class due to " + e.getMessage());
                }
        }

        private HttpResponse<JsonNode> getJsonResponseFromUrl(String city, String language, String units, Integer numberOfDaysForFutureForecast, String hostUrl) {

                initQueryMap(city, language, units, numberOfDaysForFutureForecast);
                String query = getQueryAccordingToPresentParameters();

                try {
                        return Unirest.get(hostUrl + "?" + query)
                                .header("x-rapidapi-host", X_RAPID_HOST)
                                .header("x-rapidapi-key", X_RAPID_API_KEY)
                                .asJson();
                } catch (UnirestException e) {
                        throw new IllegalArgumentException("Cannot get to" + hostUrl + "?" + query + " due to " + e.getMessage());
                }
        }

        private void initQueryMap(String city, String language, String units, Integer numberOfDaysAhead) {
                queryMap.put(NUMBER_OF_DAYS_AHEAD_QUERY, numberOfDaysAhead);
                queryMap.put(LANGUAGE_QUERY, language);
                queryMap.put(CITY_QUERY, city);
                queryMap.put(UNITS_QUERY, units);
        }

        private String getQueryAccordingToPresentParameters() {
                return queryMap.entrySet().stream()
                        .filter(ent -> ent.getValue() != null)
                        .map(ent -> ent.getKey() + "=" + ent.getValue())
                        .collect(Collectors.joining("&"));
        }
}