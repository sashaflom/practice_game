package game;

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
        // int blockAmount = random.nextInt(5) + 1;

    }

    public List<Platform> getActivePlatforms() {
        return activePlatforms;
    }

    public void setActivePlatforms(List<Platform> activePlatforms) {
        this.activePlatforms = activePlatforms;
    }
}
