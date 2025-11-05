import hero.*;
import observer.*;
import factory.HeroFactory;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("we create heroes");
        System.out.print("enter the warrior's name: ");
        Hero warrior = HeroFactory.createHero("warrior", sc.nextLine());
        System.out.print("enter the magicians name: ");
        Hero mage = HeroFactory.createHero("mage", sc.nextLine());
        LoggerObserver logger = new LoggerObserver();
        GameAnnouncer announcer = new GameAnnouncer();
        warrior.addObserver(logger);
        warrior.addObserver(announcer);
        mage.addObserver(logger);
        mage.addObserver(announcer);
        warrior.setAttackStrategy(new MeleeAttack());
        mage.setAttackStrategy(new MagicAttack());
        System.out.println("\n the fight begins! ");
        while (warrior.isAlive() && mage.isAlive()) {
            System.out.println("\n select action:");
            System.out.println("1. warrior attacks");
            System.out.println("2. mage attacks");
            System.out.println("3. change the warrior's strategy");
            System.out.println("4. change the magician's strategy");
            System.out.print("your choice:");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> warrior.attack(mage);
                case "2" -> mage.attack(warrior);
                case "3" -> {
                    System.out.print("choose a strategy for the warrior (melee/magic/ranged): ");
                    String strat = sc.nextLine();
                    warrior.setAttackStrategy(getStrategy(strat));
                }
                case "4" -> {
                    System.out.print("choose a strategy for the mage (melee/magic/ranged): ");
                    String strat = sc.nextLine();
                    mage.setAttackStrategy(getStrategy(strat));
                }
                default -> System.out.println("wrong choice");
            }
        }
        System.out.println(" the fight is over ");
        if (warrior.isAlive()) System.out.println(warrior.getName() + " won");
        else System.out.println(mage.getName() + " won");
    }
    private static AttackStrategy getStrategy(String strat) {
        return switch (strat.toLowerCase()) {
            case "melee" -> new MeleeAttack();
            case "magic" -> new MagicAttack();
            case "ranged" -> new RangedAttack();
            default -> new MeleeAttack();
        };
    }
}
