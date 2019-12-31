package weatherapp.model.futureforecastmodel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import weatherapp.deserializers.UnixTimestampDeserializer;
import weatherapp.model.Weather;

import java.time.Instant;
import java.util.List;

@Getter
@Data
@Builder
class DetailedWeather {

    @JsonDeserialize(using = UnixTimestampDeserializer.class)
    private Instant dt;
    private List<Weather> weather;
    private int humidity;
    private int pressure;
    private int clouds;
    private FutureTemperature temp;
    private int speed;
}
