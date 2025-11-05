package hero;
public class MagicAttack implements AttackStrategy {
    @Override
    public void attack(Hero attacker, Hero target) {
        int damage = 20 + (int)(Math.random() * 5);
        target.takeDamage(damage);
        System.out.println(attacker.getName() + " casts a spell on " + target.getName() + " causing " + damage + " damage");
    }
}
