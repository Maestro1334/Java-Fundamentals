package nl.inholland;

public class Config {

    private final int windowHeight;
    private final int windowWidth;

    public Config() {
        this.windowHeight = 400;
        this.windowWidth = 1200;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }
}
