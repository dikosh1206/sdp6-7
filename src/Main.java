import hero.*;
import observer.*;
import factory.HeroFactory;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("We create heroes.");
        System.out.print("Enter the warrior's name: ");
        Hero warrior = HeroFactory.createHero("warrior", sc.nextLine());

        System.out.print("Enter the magician's name: ");
        Hero mage = HeroFactory.createHero("mage", sc.nextLine());


        LoggerObserver logger = new LoggerObserver();
        GameAnnouncer announcer = new GameAnnouncer();

        warrior.addObserver(logger);
        warrior.addObserver(announcer);
        mage.addObserver(logger);
        mage.addObserver(announcer);

        // Стратегии по умолчанию
        warrior.setAttackStrategy(new MeleeAttack());
        mage.setAttackStrategy(new MagicAttack());

        System.out.println("\n The fight begins! ");

        while (warrior.isAlive() && mage.isAlive()) {
            System.out.println("\nSelect action:");
            System.out.println("1. Warrior attacks");
            System.out.println("Mage attacks");
            System.out.println("3. Change the warrior's strategy");
            System.out.println("4. Change the magician's strategy");
            System.out.print("Your choice:");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> warrior.attack(mage);
                case "2" -> mage.attack(warrior);
                case "3" -> {
                    System.out.print("Choose a strategy for the warrior (melee/magic/ranged): ");
                    String strat = sc.nextLine();
                    warrior.setAttackStrategy(getStrategy(strat));
                }
                case "4" -> {
                    System.out.print("Choose a strategy for the mage (melee/magic/ranged): ");
                    String strat = sc.nextLine();
                    mage.setAttackStrategy(getStrategy(strat));
                }
                default -> System.out.println("Wrong choice!");
            }
        }

        System.out.println(" The fight is over ");
        if (warrior.isAlive()) System.out.println(warrior.getName() + " won!");
        else System.out.println(mage.getName() + " won!");
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
