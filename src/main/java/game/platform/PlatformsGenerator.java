package game.platform;

import game.GameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlatformsGenerator {

    private List<Platform> activePlatforms;
    private Random random;
    private double monkeyHeight;
    private double monkeyJump;
    private double minY;
    private double maxY;
    private final int maxBlocksAmount = 7;
    private final double widthBetweenPlatforms = 100;

    public PlatformsGenerator(double monkeyHeight, double monkeyJump){
        activePlatforms = new ArrayList<>();
        random = new Random();
        this.monkeyHeight = monkeyHeight;
        this.monkeyJump = monkeyJump;
        minY = monkeyHeight;
        maxY = GameService.getFieldHeight() - monkeyJump;
    }

    public void generatePlatform(){
        int blockAmount = random.nextInt(maxBlocksAmount - 1) + 1;
        double y;
        if (activePlatforms.isEmpty()){
            y = maxY;
        } else{
            Platform lastAdded = getLastAddedPlatform();
            double min = lastAdded.getY() - monkeyJump;
            if(min < minY) min = minY;
            y = min + random.nextDouble() * (maxY - min);
        }
        Platform platform = new Platform("/images/floor_tile_crop.png", GameService.getScreenWidth(), y, blockAmount, 50);

        GameService.getRoot().getChildren().add(platform.getNode());
        activePlatforms.add(platform);
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
}
