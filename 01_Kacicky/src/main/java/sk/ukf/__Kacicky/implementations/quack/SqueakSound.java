package sk.ukf.__Kacicky.implementations.quack;

import org.springframework.stereotype.Component;
import sk.ukf.__Kacicky.interfaces.QuackBehavior;

@Component
public class SqueakSound implements QuackBehavior {

    @Override
    public String quack() {
        return "Sqeak, sqeak!";
    }
}
