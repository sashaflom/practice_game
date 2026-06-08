package game;

import javafx.scene.image.ImageView;

import java.util.List;
import java.util.Random;

public class PlatformsGenerator {

    private List<Platform> activePlatforms;
    private Random random;
    private final double minHeight = 200;
    private final double maxHeight = 500;

    public PlatformsGenerator(){

    }

    public void generatePlatform(){
        int blockAmount = random.nextInt(5) + 1;
        double y = minHeight + random.nextDouble() * (maxHeight - minHeight);
        Platform platform = new Platform("/images/floor_file_crop.png", 600, y, blockAmount, 50);
        for (ImageView block : platform.getBlocks()){
            GameService.getRoot().getChildren().add(block);
        }
    }

    public List<Platform> getActivePlatforms() {
        return activePlatforms;
    }

    public void setActivePlatforms(List<Platform> activePlatforms) {
        this.activePlatforms = activePlatforms;
    }
}
