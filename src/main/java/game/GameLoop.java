package game;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private long lastUpdate = 0;
    private static final int ACCELERATION_TIME = 10;
    private boolean acceleration = false;
    private long accelerationStart;

    @Override
    public void handle(long now) {
        if (lastUpdate == 0) {
            lastUpdate = now;
            return;
        }
        long diffNano = now - lastUpdate;
        double diffSec = diffNano / 1000000000.0;
        lastUpdate = now;
        if(acceleration){
            if(lastUpdate - accelerationStart >= ACCELERATION_TIME*1000000000.0){
                updateWithAcceleration(diffSec);
            }else{
                acceleration = false;
                GameService.comeBackFromAcceleration();
            }
        }
        updateGame(diffSec);
    }

    private void updateWithAcceleration(double time) {
        GameService.updateMonkey(time);
        GameService.moveBackground(time);
        GameService.moveFloor(time);
        GameService.movePlatform(time);
        // GameService.checkPlatformCollisions();
        GameService.moveBanana(time);
        // GameService.collectBananas();
        GameService.updateDistance(time);
        // GameService.changeSpeed();
        if(GameService.checkForNewPlatform()) GameService.generatePlatform();
        // GameService.checkForAcceleration();
    }

    private void updateGame(double time) {
        GameService.updateMonkey(time);
        GameService.moveBackground(time);
        GameService.moveFloor(time);
        GameService.movePlatform(time);
        GameService.checkPlatformCollisions();
        GameService.moveBanana(time);
        GameService.collectBananas();
        GameService.updateDistance(time);
        GameService.changeSpeed();
        if(GameService.checkForNewPlatform()) GameService.generatePlatform();
        GameService.checkForAcceleration();
    }

    public void setAcceleration(boolean acceleration) {
        this.acceleration = acceleration;
        accelerationStart = lastUpdate;
    }
}
