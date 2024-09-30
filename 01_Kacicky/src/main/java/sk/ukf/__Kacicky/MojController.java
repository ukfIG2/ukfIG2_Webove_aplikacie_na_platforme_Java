package sk.ukf.__Kacicky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.ukf.__Kacicky.interfaces.Duck;

import java.util.HashMap;
import java.util.Map;
////Constructor Injection
/*
@RestController
public class MojController {

    private Duck myDuck;

    @Autowired
    public MojController(
            @Qualifier("redheadDuck") Duck myDuck) {
                this.myDuck = myDuck;
    }

    @GetMapping("/")
    public String toString() {
        return myDuck.display() + " " + myDuck.swim();
    }
}*/

/////Setter Injection
/*@RestController
public class MojController {

    private Duck myDuck;

    @Autowired
    @Qualifier("rubberDuck")
    public void setDuck(Duck myDuck) {
        this.myDuck = myDuck;
    }

    @GetMapping("/")
    public String toString() {
        return myDuck.display() + " " + myDuck.swim() + " " + myDuck.performQuack() + " " + myDuck.fly() ;
    }
}*/

@RestController
public class MojController {

    private Map<String, Duck> ducks;

    @Autowired
    public MojController(
            @Qualifier("mallarDuck") Duck mallarDuck,
            @Qualifier("redheadDuck") Duck redheadDuck,
            @Qualifier("rubberDuck") Duck rubberDuck) {
        this.ducks = new HashMap<>();
        ducks.put("mallarDuck", mallarDuck);
        ducks.put("redheadDuck", redheadDuck);
        ducks.put("rubberDuck", rubberDuck);
    }

    @GetMapping("/")
    public String getAllDucks() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Duck> entry : ducks.entrySet()) {
            Duck duck = entry.getValue();
            result.append(entry.getKey())
                    .append(": ")
                    .append(duck.display())
                    .append(", ")
                    .append(duck.swim())
                    .append(", ")
                    .append(duck.performQuack())
                    .append(", ")
                    .append(duck.fly())
                    .append("<br>");
        }
        return result.toString();
    }
}

