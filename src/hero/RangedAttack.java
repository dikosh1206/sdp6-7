package hero;

public class RangedAttack implements AttackStrategy {
    @Override
    public void attack(Hero attacker, Hero target) {
        int damage = 10 + (int)(Math.random() * 15); // 10-24
        target.takeDamage(damage);
        System.out.println(attacker.getName() + " shoots at" + target.getName() + ", causing " + damage + " damage");
    }
}
