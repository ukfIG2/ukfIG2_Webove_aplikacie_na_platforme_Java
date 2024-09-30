package sk.ukf.ducksimulator;
import org.springframework.stereotype.Component;

@Component
public class MallardDuck implements Duck {

    @Override
    public String display() {
        return "Zobrazuje sa divá kačica.";
    }
    @Override
    public String swim() {
        return "Divá kačica pláva.";
    }

}
