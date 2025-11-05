package factory;
import hero.*;
public class HeroFactory {
    public static Hero createHero(String type, String name) {
        return switch(type.toLowerCase()) {
            case "warrior" -> new Warrior(name);
            case "mage" -> new Mage(name);
            case "archer" -> new Archer(name);
            default -> throw new IllegalArgumentException("unknown hero type: " + type);
        };
    }
}
