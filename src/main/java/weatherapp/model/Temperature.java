package weatherapp.model;

import lombok.Getter;

@Getter
public class Temperature {

    private  double temp;
    private double temp_min;
    private double temp_max;
    private double humidity;
    private int pressure;
    private double feels_like;
}
