package game;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private long lastUpdate = 0;

    @Override
    public void handle(long now) {
        if (lastUpdate == 0) {
            lastUpdate = now;
            return;
        }
        long diffNano = now - lastUpdate;
        double diffSec = diffNano / 1000000000.0;
        lastUpdate = now;
        updateGame(diffSec);
    }

    private void updateGame(double time) {
        GameService.moveBackground(time);
        GameService.moveFloor(time);
        GameService.movePlatform(time);
        if(GameService.checkForNewPlatform()) GameService.generatePlatform();
    }
}
