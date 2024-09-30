package sk.ukf.__Kacicky.implementations.fly;

import org.springframework.stereotype.Component;
import sk.ukf.__Kacicky.interfaces.FlyBehavior;

@Component
public class FlyWithWings implements FlyBehavior {

    @Override
    public String fly() {
        return "I am flyyyyiiinng!!!";
    }

}
