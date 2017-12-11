package logger.contracts;

public interface Target {
    void receiveDamage(int dmg);
    boolean isDead();
}