package smartgrain.com.weather.bean;

/**
 * Created by Administrator on 2017/3/18.
 */

public class WeatherForecast {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String name;
    private String url;
}
