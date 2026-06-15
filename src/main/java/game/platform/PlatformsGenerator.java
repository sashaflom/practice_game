package game.platform;

import game.GameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlatformsGenerator {

    private List<Platform> activePlatforms;
    private Random random;
    private double maxY;
    private final int maxBlocksAmount = 7;
    private final double widthBetweenPlatforms = 100;
    private static final double PLATFORM_HEIGHT = 70;
    private static final double GAP_BETWEEN_PLATFORM_LEVELS = 80;
    private static final int PLATFORM_LEVELS = 2;

    public PlatformsGenerator(double monkeyHeight, double monkeyJump){
        activePlatforms = new ArrayList<>();
        random = new Random();
        maxY = GameService.getFieldHeight() - monkeyJump;
    }

    public void generatePlatform(){
        int blockAmount = random.nextInt(maxBlocksAmount - 1) + 1;
        double y;
        if (activePlatforms.isEmpty()){
            y = maxY;
        } else{
            y = getRandomLevelY();
        }
        Platform platform = new Platform("/images/floor_tile_crop.png", GameService.getScreenWidth(), y, blockAmount, GameService.getCurrentSpeed());

        GameService.getRoot().getChildren().add(platform.getNode());
        activePlatforms.add(platform);
    }

    private double getRandomLevelY() {
        int level = random.nextInt(PLATFORM_LEVELS);
        return maxY - level * (PLATFORM_HEIGHT + GAP_BETWEEN_PLATFORM_LEVELS);
    }

    public List<Platform> getActivePlatforms() {
        return activePlatforms;
    }

    public void setActivePlatforms(List<Platform> activePlatforms) {
        this.activePlatforms = activePlatforms;
    }

    public void movePlatform(double time) {
        List<Platform> toRemove = new ArrayList<>();

        for (Platform platform : activePlatforms){
            if(!platform.update(time)){
                toRemove.add(platform);
            }
        }

        if(!toRemove.isEmpty()){
            for (Platform platform : toRemove) {
                activePlatforms.remove(platform);
                GameService.getRoot().getChildren().remove(platform.getNode());
            }
        }
    }

    public Platform getLastAddedPlatform(){
        return activePlatforms.get(activePlatforms.size() - 1);
    }

    public boolean checkForNewPlatform() {
        if(activePlatforms.isEmpty()) return true;
        Platform lastAdded = getLastAddedPlatform();
        if(lastAdded.getX() + lastAdded.getWidth() + widthBetweenPlatforms <= GameService.getScreenWidth()) return true;
        return false;
    }

    public void changeSpeed(double newSpeed){
        if (!activePlatforms.isEmpty()) for (Platform platform : activePlatforms) platform.setSpeed(newSpeed);
    }
}
