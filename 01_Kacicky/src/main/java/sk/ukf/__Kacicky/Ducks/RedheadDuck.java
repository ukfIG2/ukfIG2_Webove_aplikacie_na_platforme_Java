package sk.ukf.__Kacicky.Ducks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sk.ukf.__Kacicky.interfaces.Duck;
import sk.ukf.__Kacicky.interfaces.FlyBehavior;
import sk.ukf.__Kacicky.interfaces.QuackBehavior;

@Component
public class RedheadDuck implements Duck {

    private QuackBehavior quackBehavior;
    private FlyBehavior flyBehavior;

    @Autowired
    public RedheadDuck(@Qualifier("quackSound") QuackBehavior quackBehavior, @Qualifier("flyWithWings") FlyBehavior flyBehavior) {
        this.quackBehavior = quackBehavior;
        this.flyBehavior = flyBehavior;
    }

    @Override
    public String display() {
        return "Zobrazuje sa chochlačka žltooká.";
    }

    @Override
    public String swim() {
        return "Chochlačka žltooká pláva.";
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
