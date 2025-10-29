package observer;

public class LoggerObserver implements HeroObserver {
    @Override
    public void update(String event) {
        System.out.println("[zhans] " + event);
    }
}
