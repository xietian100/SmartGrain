package smartgrain.com.date.bean;

/**
 * Created by pc on 2017/3/9.
 */

public class AllRaceMarketing {
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String number;
    private String name;
    private String type;


    @Override
    public String toString() {
        return "AllRaceMarketing{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
