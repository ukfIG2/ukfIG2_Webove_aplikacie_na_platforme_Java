package sk.ukf.__Kacicky.Ducks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sk.ukf.__Kacicky.interfaces.Duck;
import sk.ukf.__Kacicky.interfaces.FlyBehavior;
import sk.ukf.__Kacicky.interfaces.QuackBehavior;

@Component
public class RubberDuck implements Duck {

    private QuackBehavior quackBehavior;
    private FlyBehavior flyBehavior;

    @Autowired
    public RubberDuck(@Qualifier("squeakSound") QuackBehavior quackBehavior, @Qualifier("flyNoWay") FlyBehavior flyBehavior) {
        this.quackBehavior = quackBehavior;
        this.flyBehavior = flyBehavior;
    }

    @Override
    public String display() {
        return "Zobrazuje sa gumová kačica.";
    }

    @Override
    public String swim() {
        return "Gumová kačica pláva.";
    }

    @Override
    public String performQuack() {
        return quackBehavior.quack();
    }

    @Override
    public String fly() {
        return flyBehavior.fly();
    }
}
