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
    private double minHeight;
    private double maxHeight;
    private final int maxBlocksAmount = 7;
    private final double widthBetweenPlatforms = 100;

    public PlatformsGenerator(double monkeyHeight, double monkeyJump){
        activePlatforms = new ArrayList<>();
        random = new Random();
        this.monkeyHeight = monkeyHeight;
        this.monkeyJump = monkeyJump;
        minHeight = monkeyHeight;
        maxHeight = GameService.getFieldHeight() - monkeyHeight - GameService.getFloor().getHeight();
    }

    public void generatePlatform(){
        int blockAmount = random.nextInt(maxBlocksAmount - 1) + 1;
        double y = minHeight + random.nextDouble() * (maxHeight - minHeight);
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

    public boolean checkForNewPlatform() {
        if(activePlatforms.isEmpty()) return true;
        Platform lastAdded = activePlatforms.get(activePlatforms.size() - 1);
        double x = lastAdded.getX();
        double width = lastAdded.getWidth();
        if(x + width + widthBetweenPlatforms <= GameService.getScreenWidth()) return true;
        return false;
    }
}
