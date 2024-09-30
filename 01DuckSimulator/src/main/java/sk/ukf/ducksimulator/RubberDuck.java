package sk.ukf.ducksimulator;
import org.springframework.stereotype.Component;

@Component
public class RubberDuck implements Duck {

    @Override
    public String display() {
        return "Zobrazuje sa gumová kačica...";
    }

    @Override
    public String swim() {
        return "Gumová kačica pláva...";
    }
}
