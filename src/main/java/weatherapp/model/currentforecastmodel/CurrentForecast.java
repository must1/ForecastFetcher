package weatherapp.model.currentforecastmodel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import weatherapp.deserializers.UnixTimestampDeserializer;
import weatherapp.model.Location;
import weatherapp.model.Temperature;
import weatherapp.model.Weather;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@Data
public class CurrentForecast {

    private Temperature main;
    private Coordinates coord;
    private String name;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private Location sys;
    private List<Weather> weather;
    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    private Instant dt;
}
