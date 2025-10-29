package observer;

public class GameAnnouncer implements HeroObserver {
    @Override
    public void update(String event) {
        System.out.println("[aldik] " + event);
    }
}
