package hero;
import observer.HeroObserver;
import java.util.ArrayList;
import java.util.List;

public abstract class Hero {
    private String name;
    private int health;
    private AttackStrategy attackStrategy;
    private List<HeroObserver> observers = new ArrayList<>();

    public Hero(String name, int health) {
        this.name = name;
        this.health = health;
    }
    public void setAttackStrategy(AttackStrategy strategy) {
        this.attackStrategy = strategy;
        notifyObservers(name + " changed the attack strategy to" + strategy.getClass().getSimpleName());
    }
    public void attack(Hero target) {
        if (attackStrategy != null && target.isAlive()) {
            attackStrategy.attack(this, target);
            notifyObservers(name + " atacked" + target.getName());
        } else {
            System.out.println(name + " cannot attack");
        }
    }
    public void takeDamage(int damage) {
        health -= damage;
        notifyObservers(name + " received " + damage + " damage HP remaining: " + health);
        if (health <= 0) {
            notifyObservers(name + " died");
        }
    }
    public void addObserver(HeroObserver observer) {
        observers.add(observer);
    }
    public void removeObserver(HeroObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String event) {
        for (HeroObserver obs : observers) {
            obs.update(event);
        }
    }
    public String getName() { return name; }
    public int getHealth() { return health; }
    public boolean isAlive() { return health > 0; }
}
