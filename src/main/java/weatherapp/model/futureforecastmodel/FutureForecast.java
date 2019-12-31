package weatherapp.model.futureforecastmodel;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import weatherapp.model.Location;

import java.util.List;

@Getter
@Builder
@Data
public class FutureForecast {

    private Location city;
    private int cnt;
    List<List<DetailedWeather>> list;
}
