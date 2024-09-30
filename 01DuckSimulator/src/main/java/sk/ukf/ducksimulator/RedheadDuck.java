package sk.ukf.ducksimulator;
import org.springframework.stereotype.Component;


@Component
public class RedheadDuck implements Duck {

    @Override
    public String display() {
        return "Zobrazuje sa chochlačka žltooká.";
    }

    @Override
    public String swim() {
        return "Chochlačka žltooká pláva.";
    }

}
