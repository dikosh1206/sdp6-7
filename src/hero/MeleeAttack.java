package hero;

public class MeleeAttack implements AttackStrategy {
    @Override
    public void attack(Hero attacker, Hero target) {
        int damage = 15 + (int)(Math.random() * 10); // 15-24
        target.takeDamage(damage);
        System.out.println(attacker.getName() + "attacks" + target.getName() + " in close combat, inflicting" + damage + " damage");
    }
}
