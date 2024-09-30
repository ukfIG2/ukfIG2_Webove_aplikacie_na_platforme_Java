package sk.ukf.__Kacicky.implementations.quack;

import org.springframework.stereotype.Component;
import sk.ukf.__Kacicky.interfaces.QuackBehavior;

@Component
public class QuackSound implements QuackBehavior {

    @Override
    public String quack() {
        return "Kvakam, **** kvakam!";
    }
}
