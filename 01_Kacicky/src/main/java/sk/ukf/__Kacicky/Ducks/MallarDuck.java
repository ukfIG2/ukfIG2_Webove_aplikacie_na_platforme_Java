package sk.ukf.__Kacicky.Ducks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import sk.ukf.__Kacicky.interfaces.Duck;
import sk.ukf.__Kacicky.interfaces.FlyBehavior;
import sk.ukf.__Kacicky.interfaces.QuackBehavior;

@Component //ak mu nezadame id tak prve pismeno bude male takye @Component("mallarDuck")
public class MallarDuck implements Duck {

    private QuackBehavior quackBehavior;
    private FlyBehavior flyBehavior;

    @Autowired
    public MallarDuck(@Qualifier("quackSound") QuackBehavior quackBehavior, @Qualifier("flyWithWings") FlyBehavior flyBehavior) {
        this.quackBehavior = quackBehavior;
        this.flyBehavior = flyBehavior;
    }

    @Override
    public String display() {
        return "Zobrazuje sa divá kačica.";
    }

    @Override
    public String swim() {
        return "Divá kačica pláva.";
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