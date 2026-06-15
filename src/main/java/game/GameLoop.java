package game;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private long lastUpdate = 0;
    private long accelerationStart = 0;
    private static final long ACCELERATION_DURATION = 5000000000L;
    private boolean acceleration = false;

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
            if(lastUpdate - accelerationStart < ACCELERATION_DURATION){
                updateGameWithAcceleration(diffSec);
            }else{
                acceleration = false;
                GameService.comeBackFromAcceleration();
                updateGame(diffSec);
            }
        }else{
            updateGame(diffSec);
        }
    }

    private void updateGameWithAcceleration(double time) {
        GameService.moveBackground(time);
        GameService.moveFloor(time);
        GameService.movePlatform(time);
        GameService.moveBanana(time);
        GameService.moveMagnet(time);
        GameService.collectBananas();
        GameService.collectMagnet();
        GameService.updateDistance(time);
        if(GameService.checkForNewPlatform()) GameService.generatePlatform();
    }

    private void updateGame(double time) {
        GameService.updateMonkey(time);
        GameService.moveBackground(time);
        GameService.moveFloor(time);
        GameService.movePlatform(time);
        GameService.checkPlatformCollisions();
        GameService.moveBanana(time);
        GameService.collectMagnet();
        GameService.moveMagnet(time);
        GameService.collectBananas();
        GameService.updateDistance(time);
        GameService.changeSpeed();
        if(GameService.checkForNewPlatform()) GameService.generatePlatform();
        GameService.checkForAcceleration();
    }

    public void accelerationTrue() {
        acceleration = true;
        accelerationStart = lastUpdate;
    }
}
