package singleton;
public class GameLogger {
    private static GameLogger instance;

    private GameLogger() {}

    public static GameLogger getInstance() {
        if (instance == null) {
            instance = new GameLogger();
        }
        return instance;
    }
    public void log(String message) {
        System.out.println("GAME LOG " + message);
    }
}
